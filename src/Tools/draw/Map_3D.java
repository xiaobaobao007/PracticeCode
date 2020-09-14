package Tools.draw;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Map_3D extends JPanel {

	public static JFrame jFrame;

	int JFRAME_WIDTH = 1000;
	int JFRAME_HEIGHT = 1000;

	enum XYZ {

		X(0, 0),
		Y(0, 0),
		Z(0, 0.05);

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
			degree += change;
			init();
		}
	}

	public Map_3D() {

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
			}
		}).start();
		// });
	}

	public static void main(String[] args) {
		new Map_3D();
	}

	static double length = 50.0;
	static double[][] points = {{-length, -length, length}, {length, -length, length}, {length, length, length}, {-length, length, length},
			{-length, -length, -length}, {length, -length, -length}, {length, length, -length}, {-length, length, -length}};
	static double[] e = {100, 100, 100};

	// static {
	// static double[] c = {200.0, 200.0, 200.0};
	// 	for (int i = 0; i < points.length; i++) {
	// 		for (int j = 0; j < 3; j++) {
	// 			points[i][j] -= c[j];
	// 		}
	// 	}
	// }

	double[] d = new double[6];
	int[] b = new int[6];

	int center_x = 400;
	int center_y = 400;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);

		// get(0, 0);
		// System.out.println(d[0] + "-" + d[1] + "-" + d[2]);
		for (int i = 3, j = 0; i >= 0; j = i--) {
			// System.out.println(i + "-" + j);
			get(i, 0);
			get(j, 3);
			paint___(g);
			// System.out.println(Arrays.toString(d));
			// System.out.println(Arrays.toString(b));
		}

		for (int i = 7, j = 4; i >= 4; j = i--) {
			get(i, 0);
			get(j, 3);
			paint___(g);
		}

		// for (int i = 0, j = 4; i < 4; j = 4 + ++i) {
		// 	get(i, 0);
		// 	get(j, 3);
		// 	paint___(g);
		// }

		get(0, 0);
		get(6, 3);
		paint___(g);

		get(1, 0);
		get(7, 3);
		paint___(g);

		get(2, 0);
		get(4, 3);
		paint___(g);

		get(3, 0);
		get(5, 3);
		paint___(g);
	}

	public void paint___(Graphics g) {
		g.drawLine(center_x + b[0], center_y + b[1], center_x + b[3], center_y + b[4]);
	}

	public void get(int index, int offset) {
		d[offset] = XYZ.Y.cos * (XYZ.Z.sin * (points[index][1]) + XYZ.Z.cos * (points[index][0])) - XYZ.Y.sin * (points[index][2]);
		d[offset + 1] = XYZ.X.sin * (XYZ.Y.cos * (points[index][2]) + XYZ.Y.sin * (XYZ.Z.sin * points[index][1] + XYZ.Z.cos * (points[index][0])))
								+ XYZ.X.cos * (XYZ.Z.cos * points[index][1] - XYZ.Z.sin * points[index][0]);
		d[offset + 2] = XYZ.X.cos * (XYZ.Y.cos * (points[index][2]) + XYZ.Y.sin * (XYZ.Z.sin * points[index][1] + XYZ.Z.cos * (points[index][0])))
								- XYZ.X.sin * (XYZ.Z.cos * points[index][1] - XYZ.Z.sin * points[index][0]);

		b[offset] = (int) ((d[offset] - e[0]) * e[2] / d[offset + 2]);
		b[offset + 1] = (int) ((d[offset + 1] - e[1]) * e[2] / d[offset + 2]);

		// b[offset] = (int) ((d[offset] - e[0]) * e[2] / d[offset + 1]);
		// b[offset + 1] = (int) ((d[offset + 2] - e[2]) * e[2] / d[offset + 1]);



	}


	// public void getX(int array[], int index, int offset) {
	// 	array[offset] = (int) (pointMap[index][offset]);
	// 	array[offset + 1] = (int) (pointMap[index][offset + 1] * XYZ.Y.con - pointMap[index][offset + 2] * XYZ.Y.sin);
	// 	array[offset + 2] = (int) (array[offset + 1] * XYZ.Y.sin + array[offset + 2] * XYZ.Y.con);
	//
	// 	array[offset + 1] = (int) (array[offset] * XYZ.Y.con + array[offset + 2] * XYZ.Y.sin);
	// 	array[offset + 1] = (int) (-array[offset] * XYZ.Y.sin + array[offset + 2] * XYZ.Y.con);
	// 	array[offset + 2] = (int) (array[offset] * XYZ.Y.con - array[offset + 1] * XYZ.Y.sin);
	// 	array[offset + 2] = (int) (-array[offset] * XYZ.Y.sin + array[offset + 1] * XYZ.Y.con);
	// }
}
