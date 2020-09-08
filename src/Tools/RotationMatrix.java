package Tools;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * 旋转的矩阵
 */
public class RotationMatrix extends JPanel {

	public static JFrame jFrame;

	public static int JFRAME_WIDTH = 818;
	public static int JFRAME_HEIGHT = 848;

	double degrees = 0.0;

	public RotationMatrix() {

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

		new Thread(() -> {
			for (; ; ) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				degrees += 0.05;

				this.repaint();
			}
		}).start();
	}

	public static void main(String[] args) {
		new RotationMatrix();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		int m = 10;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				double x = i * 30.0;
				double y = j * 30.0;

				double r = Math.sqrt(x * x + y * y);
				double circle = Math.toRadians(degrees + Math.asin(y / r) / 2 / Math.PI * 360.0);
				x = Math.sin(circle) * r;
				y = Math.cos(circle) * r;

				g.fillOval(400 + (int) x, 400 + (int) y, 10, 10);
			}
		}
	}

}
