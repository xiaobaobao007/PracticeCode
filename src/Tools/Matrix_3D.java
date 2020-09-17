package Tools;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Matrix_3D extends JPanel {

	public static JFrame jFrame;

	//正方体边长的一半长度
	static double length = 300.0;
	//正方体坐标
	static double[][] points = {{-length, -length, length}, {length, -length, length}, {length, length, length}, {-length, length, length},
			{-length, -length, -length}, {length, -length, -length}, {length, length, -length}, {-length, length, -length}};
	//正方体的面
	static int[][] side = {
			{0, 1, 2, 3},
			{3, 2, 6, 7},
			{2, 6, 5, 1},
			{1, 5, 4, 0},
			{0, 4, 7, 3},
			{4, 5, 6, 7}};
	//每个面对应的颜色
	static Color[] colors = {Color.RED, Color.ORANGE, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.GREEN};
	//投影平面的中心点
	static double[] canvas = {0, 500, 500};
	//投影平面的法向量
	static double[] canvas_c = {0, 100, 100};
	//投影平面的上的一点，此点到canvas视为投影平面的最上方
	static double[] canvas_p = {0, 400, 600};
	//摄像机向量
	static double[] eye = {0, 1000, 1000};
	//计算结果缓存
	double[][] d = new double[8][3];
	//立方体的各个面的法向量
	double[] o_o = new double[3];
	//缓存二维坐标
	int[][] b = new int[8][2];
	int center_x = 500;
	int center_y = 500;
	int JFRAME_WIDTH = 1000;
	int JFRAME_HEIGHT = 1000;

	enum XYZ {

		//起始旋转角度和变化量
		X(0, 0.1),
		Y(0, 0.1),
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

	public static void main(String[] args) {
		new Matrix_3D();
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

		//另起线程
		new Thread(() -> {
			for (; ; ) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//角度改变
				XYZ.X.change();
				XYZ.Y.change();
				XYZ.Z.change();
				//绘图
				this.repaint();
			}
		}).start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//计算八个点投影后的坐标
		for (int i = 0; i < 8; i++) {
			calcute_3d_2d(i);
		}
		//绘图
		for (int i = side.length - 1; i >= 0; i--) {
			calcute_o_o(g, i, side[i]);
		}
	}

	//变量外定义，避免重复定义
	int[] xx = new int[4];
	int[] yy = new int[4];

	public void calcute_o_o(Graphics g, int index, int[] a1) {
		for (int i = 0; i < 3; i++) {
			o_o[i] = (d[a1[0]][i] + d[a1[1]][i] + d[a1[2]][i] + d[a1[3]][i]) / 4;
		}
		int j = 0;
		for (int i = 0; i < 3; i++) {
			j += o_o[i] * (eye[i] - o_o[i]);
		}
		if (j >= 0) {
			//平面法向量与视线夹角小于90度
			xx[0] = center_x + b[a1[0]][0];
			xx[1] = center_x + b[a1[1]][0];
			xx[2] = center_x + b[a1[2]][0];
			xx[3] = center_x + b[a1[3]][0];

			yy[0] = center_y + b[a1[0]][1];
			yy[1] = center_y + b[a1[1]][1];
			yy[2] = center_y + b[a1[2]][1];
			yy[3] = center_y + b[a1[3]][1];

			g.setColor(colors[index]);
			g.fillPolygon(xx, yy, 4);
		}
	}

	//计算3d点到2d点
	public void calcute_3d_2d(int index) {
		d[index][0] = points[index][0];
		d[index][1] = points[index][1] * XYZ.X.cos - points[index][2] * XYZ.X.sin;
		d[index][2] = points[index][1] * XYZ.X.sin + points[index][2] * XYZ.X.cos;

		double qwe = d[index][0] * XYZ.Y.cos + d[index][2] * XYZ.Y.sin;
		d[index][2] = -d[index][0] * XYZ.Y.sin + d[index][2] * XYZ.Y.cos;

		double rty = qwe * XYZ.Z.cos - d[index][1] * XYZ.Z.sin;
		d[index][1] = qwe * XYZ.Z.sin + d[index][1] * XYZ.Z.cos;
		d[index][0] = rty;

		double[] t = calPlaneLineIntersectPoint(d[index]);
		if (t == null) {
			//平行
			return;
		}

		//计算三条边的长度
		double o = gerDistance(t, canvas_p);
		double p = gerDistance(canvas_p, canvas);
		double i = gerDistance(t, canvas);
		double ii = Math.sqrt(i);

		//根据三边求得cos值
		double cosA = Math.abs((i + p - o) / (2 * ii * Math.sqrt(p)));
		//更具cos求sin
		double sina = Math.sqrt((1 - Math.pow(cosA, 2)));

		//计算投影平面上的2d坐标
		double xx = ii * sina * (t[0] >= canvas[0] ? 1 : -1);
		double yy = ii * cosA * (t[1] >= canvas[1] ? 1 : -1);

		//缓存数据
		b[index][0] = (int) xx;
		b[index][1] = (int) yy;
	}

	//计算点经过投影在投影平面的3d坐标
	private double[] calPlaneLineIntersectPoint(double[] linePoint) {
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
		v1 = eye[0] - linePoint[0];
		v2 = eye[1] - linePoint[1];
		v3 = eye[2] - linePoint[2];
		// 直线经过的一点坐标
		m1 = eye[0];
		m2 = eye[1];
		m3 = eye[2];
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

	//计算两个点之间的间距
	public double gerDistance(double[] a, double[] b) {
		double i = 0;
		for (int j = 0; j < 3; j++) {
			i += Math.pow(Math.abs(b[j] - a[j]), 2);
		}
		return i;
	}

}
