package Tools.draw;

import java.awt.*;

import javax.swing.*;

public class Draw extends JFrame {

	private static final long serialVersionUID = 1L;
	/*
	 * 变换矩阵
	 */
	public float[][] tranMatrix = {{0.701f, 0, -0.701f, -0.002f}, {0, 1, 0, 0.005f},
			{0.701f, 0, 0.701f, 0.0007f}, {0, 0, 1, 1}};

	public static Point3[] points3 = new Point3[8];
	public static Point2[] points2 = new Point2[8];

	static {
		points3[0] = new Point3(0, 0, 0);
		points3[1] = new Point3(0, 0, 100);
		points3[2] = new Point3(0, 100, 0);
		points3[3] = new Point3(100, 0, 0);
		points3[4] = new Point3(100, 100, 0);
		points3[5] = new Point3(0, 100, 100);
		points3[6] = new Point3(100, 0, 100);
		points3[7] = new Point3(100, 100, 100);
	}

	public Draw(String name) {
		this.setTitle(name);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		this.repaint();
	}

	public void bmy() {
		this.repaint();
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawPicture(g);
		drawRectangl3D(g);
	}

	/*
	 * 在Java中我们只能够画二维图形但是我们想要呈现三维的效果 在画图的过程中我们保证
	 * 在yz轴 上图形不发生形变 x轴与y轴45度 z轴135度
	 * 因此我们需要一个三维的点转为二维的点的公式
	 *
	 * x2=y3-x3/2; y2=z3-x3/2; 三维图形可以用八个顶点来表示 也可以12条边来表示
	 */

	public void drawPicture(Graphics g) {
		/*
		 * 这是一个边长为一百像素的三维正方体图
		 */
		g.drawLine(60, 60, 160, 60);
		g.drawLine(160, 60, 110, 110);
		g.drawLine(110, 110, 10, 110);
		g.drawLine(10, 110, 60, 60);
		g.drawLine(10, 110, 10, 210);
		g.drawLine(10, 210, 110, 210);
		g.drawLine(110, 210, 110, 110);
		g.drawLine(10, 210, 60, 160);
		g.drawLine(60, 160, 160, 160);
		g.drawLine(60, 160, 60, 60);
		g.drawLine(160, 160, 160, 60);
		g.drawLine(110, 210, 160, 160);
	}

	public void transform() {
		for (int k = 0; k < 8; k++) {
			points3[k].x = points3[k].x * tranMatrix[0][0] + points3[k].y * tranMatrix[1][0]
								   + points3[k].z * tranMatrix[2][0] + points3[k].t * this.tranMatrix[3][0];

			points3[k].y = points3[k].x * tranMatrix[0][1] + points3[k].y * tranMatrix[1][1]
								   + points3[k].z * tranMatrix[2][1] + points3[k].t * tranMatrix[3][1];

			points3[k].z = points3[k].x * tranMatrix[0][2] + points3[k].y * tranMatrix[1][2]
								   + points3[k].z * tranMatrix[2][2] + points3[k].t * tranMatrix[3][2];

			points3[k].t = points3[k].x * tranMatrix[0][3] + points3[k].y * tranMatrix[1][3]
								   + points3[k].z * tranMatrix[2][3] + points3[k].t * tranMatrix[3][3];
			points3[k].x /= points3[k].t;
			points3[k].y /= points3[k].t;
			points3[k].z /= points3[k].t;
			points3[k].t = 1;
		}
		for (int k = 0; k < 8; k++) {
			points2[k] = new Point2(300 + points3[k].y - (points3[k].x / 2), 300 + points3[k].z - (points3[k].x / 2));
		}
	}

	public void drawRectangl3D(Graphics g) {
		g.drawLine(points2[0].getX(), points2[0].getY(), points2[1].getX(), points2[1].getY());
		g.drawLine(points2[0].getX(), points2[0].getY(), points2[2].getX(), points2[2].getY());
		g.drawLine(points2[0].getX(), points2[0].getY(), points2[3].getX(), points2[3].getY());
		g.drawLine(points2[1].getX(), points2[1].getY(), points2[5].getX(), points2[5].getY());
		g.drawLine(points2[1].getX(), points2[1].getY(), points2[6].getX(), points2[6].getY());
		g.drawLine(points2[2].getX(), points2[2].getY(), points2[4].getX(), points2[4].getY());
		g.drawLine(points2[2].getX(), points2[2].getY(), points2[5].getX(), points2[5].getY());
		g.drawLine(points2[3].getX(), points2[3].getY(), points2[4].getX(), points2[4].getY());
		g.drawLine(points2[3].getX(), points2[3].getY(), points2[6].getX(), points2[6].getY());
		g.drawLine(points2[4].getX(), points2[4].getY(), points2[7].getX(), points2[7].getY());
		g.drawLine(points2[5].getX(), points2[5].getY(), points2[7].getX(), points2[7].getY());
		g.drawLine(points2[6].getX(), points2[6].getY(), points2[7].getX(), points2[7].getY());
	}

	public static void main(String[] args) {
		Draw window = new Draw("画图");
		window.bmy();
		// try {
		// 	Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// 	e.printStackTrace();
		// }
		window.transform();
		window.bmy();
	}
}

class Point3 {
	public float x, y, z, t;

	public Point3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = 1;
	}
}

class Point2 {
	float x, y;

	Point2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}
}