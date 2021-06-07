package Tools;

import java.awt.*;

import javax.swing.*;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/6/7，17:58:49
 */

public class SquareToDiamond extends JPanel {

	public static JFrame jFrame;
	int JFrame_WIDTH = 800;
	int JFrame_HEIGHT = 800;

	int start_x = 40;
	int start_y = 40;

	int X_WEIGHT = 104;
	int Y_WEIGHT = 120;

	int[][] point = {{52, 0}, {104, 30}, {104, 90}, {52, 120}, {0, 90}, {0, 30}};

	public static void main(String[] args) {
		new SquareToDiamond();
	}

	public SquareToDiamond() {
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

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int x = 0;
		int y = 0;
		for (int y_index = 0; y_index < 10; y_index++) {
			for (int x_index = 0; x_index < 10; x_index++) {
				g.drawString(x_index + " " + y_index, x + 40, y + 52);
				paint(g, x, y);
				x += 104;
			}
			if (y_index % 2 == 0) {
				x = 52;
			} else {
				x = 0;
			}
			y += 90;
		}
	}

	private void paint(Graphics g, int x, int y) {
		for (int i = point.length - 1, j = 0; j < point.length; i = j++) {
			g.drawLine(point[i][0] + x, point[i][1] + y, point[j][0] + x, point[j][1] + y);
		}
	}

}