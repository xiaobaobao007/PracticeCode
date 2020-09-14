package Tools.draw;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Map_3D_Old extends JPanel {

	public static JFrame jFrame;

	int JFRAME_WIDTH = 1000;
	int JFRAME_HEIGHT = 1000;

	enum XYZ {

		X(50, 0.1),
		Y(0, 0),
		Z(0, 0.01);

		private final double change;

		private double degree;
		private double radians;
		double sin;
		double cos;

		XYZ(double init, double change) {
			this.degree = init;
			this.change = change;
		}

		public void init() {
			radians = Math.toRadians(degree);
			sin = Math.sin(radians);
			cos = Math.cos(radians);
		}

		public void change() {
			degree = (degree + change) % 360;
			init();
		}
	}

	public Map_3D_Old() {

		jFrame = new JFrame();
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_X = (int) (ScreenSize.getWidth() - JFRAME_WIDTH) / 2;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFRAME_HEIGHT) / 2;
		jFrame.setLocation(JFrame_X, JFrame_Y);
		jFrame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
		jFrame.setLayout(null);
		this.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		this.setLayout(null);

		jFrame.add(this);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

		XYZ.X.init();
		XYZ.Y.init();
		XYZ.Z.init();

		new Thread(() -> {
			for (; ; ) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				XYZ.X.change();
				XYZ.Y.change();
				XYZ.Z.change();
				this.repaint();
				// get(0);
				// System.out.println(Arrays.toString(d[0]));
			}
		}).start();
		// });
	}

	public static void main(String[] args) {
		new Map_3D_Old();
	}

	static double length = 50.0;
	static double[][] points = {{-length, -length, length}, {length, -length, length}, {length, length, length}, {-length, length, length},
			{-length, -length, -length}, {length, -length, -length}, {length, length, -length}, {-length, length, -length}};
	static double[] e = {0, 0, 10};

	// static {
	// 	double[] c = {200.0, 200.0, 200.0};
	// 	for (int i = 0; i < points.length; i++) {
	// 		for (int j = 0; j < 3; j++) {
	// 			points[i][j] -= c[j];
	// 		}
	// 	}
	// }

	double[][] d = new double[8][3];
	int[][] b = new int[8][2];

	int center_x = 400;
	int center_y = 400;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// g.setColor(Color.RED);

		// get(0);
		//
		// System.out.println(Arrays.toString(d[0]));

		for (int i = 0; i < 4; i++) {
			get(i);
		}

		if ((45 <= XYZ.X.degree && XYZ.X.degree <= 135) || (225 <= XYZ.X.degree && XYZ.X.degree <= 315)) {
			paint___(g, 0, 2);
			paint___(g, 1, 3);
			paint___(g, 2, 3);
			paint___(g, 0, 1);
		} else {
			paint___(g, 0, 1);
			paint___(g, 1, 2);
			paint___(g, 2, 3);
			paint___(g, 3, 0);
		}


		//
		// paint___(g, 4, 5);
		// paint___(g, 5, 6);
		// paint___(g, 6, 7);
		// paint___(g, 7, 4);
		//
		// paint___(g, 0, 6);
		// paint___(g, 1, 7);
		// paint___(g, 2, 4);
		// paint___(g, 3, 5);

	}

	public void paint___(Graphics g, int i, int j) {
		g.drawLine(center_x + b[i][0], center_y + b[i][1], center_x + b[j][0], center_y + b[j][1]);
	}


	public void get(int index) {
		d[index][0] = points[index][0];
		d[index][1] = points[index][1] * XYZ.X.cos - points[index][2] * XYZ.X.sin;
		d[index][2] = points[index][1] * XYZ.X.sin + points[index][2] * XYZ.X.cos;

		double qwe = d[index][0] * XYZ.Y.cos + d[index][2] * XYZ.Y.sin;
		d[index][2] = -d[index][0] * XYZ.Y.sin + d[index][2] * XYZ.Y.cos;

		double rty = qwe * XYZ.Z.cos - d[index][1] * XYZ.Z.sin;
		d[index][1] = qwe * XYZ.Z.sin + d[index][1] * XYZ.Z.cos;
		d[index][0] = rty;

		b[index][0] = (int) ((d[index][0] - e[0]) * e[2] / d[index][2]);
		b[index][1] = (int) ((d[index][1] - e[1]) * e[2] / d[index][2]);
	}

}
