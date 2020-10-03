package Swing;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class ThreeCircle extends JPanel {
	enum Circle {

		ONE(Color.GREEN, 480, 300),
		TWO(Color.RED, ONE.M, 150),
		THREE(Color.GREEN, TWO.M, 0);

		Color color;
		int L;
		int M;
		Circle next;

		Circle(Color color, int l, int m) {
			this.color = color;
			L = l;
			M = m;
		}

	}

	static {
		Circle.ONE.next = Circle.TWO;
		Circle.TWO.next = Circle.THREE;
		Circle.THREE.next = Circle.ONE;
	}

	public static JFrame jFrame;
	int JFrame_WIDTH = 600;
	int JFrame_HEIGHT = 600;
	Circle circle = Circle.ONE;

	public static void main(String[] args) {
		new ThreeCircle();
	}

	public ThreeCircle() {
		jFrame = new JFrame();
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_X = (int) (ScreenSize.getWidth() - JFrame_WIDTH) / 2;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFrame_HEIGHT) / 2;
		jFrame.setLocation(JFrame_X, JFrame_Y);
		jFrame.setSize(JFrame_WIDTH, JFrame_HEIGHT);
		jFrame.setLayout(null);
		this.setBounds(0, 0, JFrame_WIDTH, JFrame_HEIGHT);
		this.setLayout(null);

		jFrame.add(this);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

		new Thread(() -> {
			for (; ; ) {
				this.repaint();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				circle = circle.next;
			}
		}).start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(circle.color);
		paintCircle(g, circle.L);
		if (circle.M > 0) {
			g.setColor(Color.WHITE);
			paintCircle(g, circle.M);
		}

		// g.setColor(Circle.ONE.color);
		// paintCircle(g, Circle.ONE.L);
		//
		// g.setColor(Circle.TWO.color);
		// paintCircle(g, Circle.TWO.L);
		//
		// g.setColor(Circle.THREE.color);
		// paintCircle(g, Circle.THREE.L);
	}

	public void paintCircle(Graphics g, int L) {
		g.fillOval(JFrame_WIDTH / 2 - L / 2, JFrame_WIDTH / 2 - L / 2, L, L);
	}

}