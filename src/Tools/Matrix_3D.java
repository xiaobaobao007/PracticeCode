package Tools;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Matrix_3D extends JPanel {

	public static JFrame jFrame;

	static double length = 300.0;
	static double[][] points = {{-length, -length, length}, {length, -length, length}, {length, length, length}, {-length, length, length},
			{-length, -length, -length}, {length, -length, -length}, {length, length, -length}, {-length, length, -length}};

	static double[] canvas = {0, 500, 500};
	static double[] canvas_c = {0, 100, 100};
	static double[] canvas_p = {0, 400, 600};
	static double[] e = {0, 600, 600};

	double[][] d = new double[8][3];
	int[][] b = new int[8][2];

	int center_x = 500;
	int center_y = 500;
	int JFRAME_WIDTH = 1000;
	int JFRAME_HEIGHT = 1000;

	enum XYZ {

		X(0, 0),
		Y(0, 0),
		Z(0, 0.1);

		private final double change;

		private double degree;
		double sin;
		double cos;

		XYZ(double init, double change) {
			this.degree = init;
			this.change = change;
		}

		public void init() {
			double radians = Math.toRadians(degree);
			sin = Math.sin(radians);
			cos = Math.cos(radians);
		}

		public void change() {
			degree = (degree + change) % 360;
			init();
		}
	}

	public Matrix_3D() {

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
	}

	public static void main(String[] args) {
		new Matrix_3D();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < 8; i++) {
			get(i);
		}

		paint___(g, 0, 1);
		paint___(g, 1, 2);
		paint___(g, 2, 3);
		paint___(g, 3, 0);

		paint___(g, 0, 4);
		paint___(g, 1, 5);
		paint___(g, 2, 6);
		paint___(g, 3, 7);

		paint___(g, 4, 5);
		paint___(g, 5, 6);
		paint___(g, 6, 7);
		paint___(g, 7, 4);

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

		double[] t = aaaaCalPlaneLineIntersectPoint(d[index]);
		if (t == null) {
			return;
		}

		double o = gerDistance(t, canvas_p);
		double p = gerDistance(canvas_p, canvas);
		double i = gerDistance(t, canvas);
		double ii = Math.sqrt(i);

		double cosA = Math.abs((i + p - o) / (2 * ii * Math.sqrt(p)));
		double sina = Math.sqrt((1 - Math.pow(cosA, 2)));

		double xx = ii * sina * (t[0] >= canvas[0] ? 1 : -1);
		double yy = ii * cosA * (t[1] >= canvas[1] ? 1 : -1);

		b[index][0] = (int) xx;
		b[index][1] = (int) yy;
	}

	private double[] aaaaCalPlaneLineIntersectPoint(double[] linePoint) {
		double[] returnResult = new double[3];
		double vp1, vp2, vp3, n1, n2, n3, v1, v2, v3, m1, m2, m3, t, vpt;
		// 平面的法线向量
		vp1 = canvas_c[0];
		vp2 = canvas_c[1];
		vp3 = canvas_c[2];
		// 平面经过的一点坐标
		n1 = canvas[0];
		n2 = canvas[1];
		n3 = canvas[2];
		// 直线的方向向量
		v1 = e[0] - linePoint[0];
		v2 = e[1] - linePoint[1];
		v3 = e[2] - linePoint[2];
		// 直线经过的一点坐标
		m1 = e[0];
		m2 = e[1];
		m3 = e[2];
		vpt = v1 * vp1 + v2 * vp2 + v3 * vp3;
		if (vpt == 0) {
			returnResult = null;
		} else {
			t = ((n1 - m1) * vp1 + (n2 - m2) * vp2 + (n3 - m3) * vp3) / vpt;
			returnResult[0] = m1 + v1 * t;
			returnResult[1] = m2 + v2 * t;
			returnResult[2] = m3 + v3 * t;
		}
		return returnResult;
	}

	public double gerDistance(double[] a, double[] b) {
		double i = 0;
		for (int j = 0; j < 3; j++) {
			i += Math.pow(Math.abs(b[j] - a[j]), 2);
		}
		return i;
	}

}
