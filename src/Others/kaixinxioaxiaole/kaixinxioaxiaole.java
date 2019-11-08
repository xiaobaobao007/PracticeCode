package Others.kaixinxioaxiaole;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.*;

public class kaixinxioaxiaole extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	int JFrame_Width = 600;//������
	int JFrame_Height = 600;//����߶�
	int JPanel_Width = 500;//�����
	int JPanel_Height = 500;//���߶�
	int panel_mid = 10;//������

	int width;//������
	int height;//����߶�

//    public static int top;
//    public static int left;

	private final int length = 8;
	private int[][] Map = new int[length][length];
	private int[][][] MapLocation = new int[length][length][2];
	private final int colorNum = 5;
	private Color[] colors = new Color[8];

	kaixinxioaxiaole() {
		JFrame jFrame = new JFrame("���ʼ�");
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_X = (int) (ScreenSize.getWidth() - JFrame_Width) / 2;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFrame_Height) / 2;
		jFrame.setLocation(JFrame_X, JFrame_Y);
		jFrame.setSize(JFrame_Width, JFrame_Height);
		jFrame.setLayout(null);
		gameStart();
		new Thread(this).start();
		this.setLayout(null);
		this.setBounds(45, 34, JPanel_Width, JPanel_Height);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		jFrame.add(this);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
//        Insets insets = jFrame.getInsets();
//        top = insets.top;
//        left = insets.left;
	}

	public static void main(String[] args) {
		new kaixinxioaxiaole();
	}

	public void gameStart() {
		colors[0] = Color.RED;
		colors[1] = Color.blue;
		colors[2] = Color.green;
		colors[3] = Color.ORANGE;
		colors[4] = Color.PINK;
		colors[5] = Color.yellow;
		colors[6] = Color.CYAN;
		colors[7] = Color.MAGENTA;
		while (true) {
			restMap();
			if (gameCanContinue()) break;
		}
		soutMap();
	}

	/**
	 * ��ͼ��ʼ��
	 */
	private void restMap() {
		width = (JPanel_Width + panel_mid) / length;
		height = (JPanel_Height + panel_mid) / length;
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < length; x++) {
				Map[y][x] = restMap(x, y);
				MapLocation[y][x][0] = width * x;
				MapLocation[y][x][1] = height * y;
				System.out.print(y + "," + x + "//");
			}
			System.out.println();
		}
		System.out.println();
		width -= panel_mid;
		height -= panel_mid;
	}

	public void soutMap() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.printf("%-2d", Map[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * ��ͼ��ʼ��һ��λ��
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private int restMap(int x, int y) {
		int color;
		while (true) {
			color = new Random().nextInt(colorNum) + 1;
			Map[y][x] = color;
			int num = findParentNum(x, y);
			if (num <= 2) {
				break;
			}
		}
		return color;
	}

	/**
	 * �ж���Ϸ�Ƿ�����
	 *
	 * @return
	 */
	private boolean gameCanContinue() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (swip(i, j)) return true;
			}
		}
		return false;
	}

	/**
	 * �������ж��Ƿ����
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean swip(int x, int y) {
		if (swip(x, y, 0, true)) {
			return true;
		}
		if (swip(x, y, 1, true)) {
			return true;
		}
		if (swip(x, y, 2, true)) {
			return true;
		}
		return swip(x, y, 3, true);
	}

	/**
	 * ����
	 *
	 * @param x
	 * @param y
	 * @param direct
	 * @return
	 */
	private boolean swip(int x, int y, int direct, boolean isTest) {
		switch (direct) {
			case 0://up
				if (y > 0) {
					int first = Map[y][x];
					int second = Map[y - 1][x];
					Map[y][x] = second;
					Map[y - 1][x] = first;
					boolean is = false;
					if (findParentNum(first, x, y - 1) > 2) is = true;
					if (findParentNum(second, x, y) > 2) is = true;
					if (isTest) {
						Map[y][x] = first;
						Map[y - 1][x] = second;
					}
					return is;
				}
				break;
			case 1://right
				if (x + 1 < length) {
					int first = Map[y][x];
					int second = Map[y][x + 1];
					Map[y][x] = second;
					Map[y][x + 1] = first;
					boolean is = false;
					if (findParentNum(first, x + 1, y) > 2) is = true;
					if (findParentNum(second, x, y) > 2) is = true;
					if (isTest) {
						Map[y][x] = first;
						Map[y][x + 1] = second;
					}
					return is;
				}
				break;
			case 2://down
				if (y + 1 < length) {
					int first = Map[y][x];
					int second = Map[y + 1][x];
					Map[y][x] = second;
					Map[y + 1][x] = first;
					boolean is = false;
					if (findParentNum(first, x, y + 1) > 2) is = true;
					if (findParentNum(second, x, y) > 2) is = true;
					if (isTest) {
						Map[y][x] = first;
						Map[y + 1][x] = second;
					}
					return is;
				}
				break;
			case 3://left
				if (x > 0) {
					int first = Map[y][x];
					int second = Map[y][x - 1];
					Map[y][x] = second;
					Map[y][x - 1] = first;
					boolean is = false;
					if (findParentNum(first, x - 1, y) > 2) is = true;
					if (findParentNum(second, x, y) > 2) is = true;
					if (isTest) {
						Map[y][x] = first;
						Map[y][x - 1] = second;
					}
					return is;
				}
				break;
		}
		return false;
	}

	/**
	 * ��������
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private int findParentNum(int x, int y) {
		return findParentNum(Map[y][x], x, y, new int[length][length]);
	}

	/**
	 * ��������
	 *
	 * @param color
	 * @param x
	 * @param y
	 * @return
	 */
	private int findParentNum(int color, int x, int y) {
		return findParentNum(color, x, y, new int[length][length]);
	}

	/**
	 * ��������
	 *
	 * @param color
	 * @param x
	 * @param y
	 * @param restMap
	 * @return
	 */
	private int findParentNum(int color, int x, int y, int[][] restMap) {
		int num = 0;
		if (x >= 0 && x < length && y >= 0 && y < length) {
			if (restMap[y][x] != 0 || Map[y][x] != color) {
				return 0;
			}
			restMap[y][x] = 1;
			num++;
			num += findParentNum(color, x + 1, y, restMap);
			num += findParentNum(color, x, y + 1, restMap);
			num += findParentNum(color, x - 1, y, restMap);
			num += findParentNum(color, x, y - 1, restMap);
			return num;
		} else {
			return num;
		}
	}

	/**
	 * �������ڵķ���
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void swip(int x1, int y1, int x2, int y2) {
		if (x1 == x2) {
			if (y1 + 1 == y2) {
				swip(x1, y1, 2);
			} else if (y1 == y2 + 1) {
				swip(x1, y1, 0);
			}
		} else if (y1 == y2) {
			if (x1 + 1 == x2) {
				swip(x1, y1, 1);
			} else if (x1 == x2 + 1) {
				swip(x1, y1, 3);
			}
		}
	}

	public void change(int x, int y) {
		int color = getMapValue(x, y);
		change(x, y, color);
	}

	public void change(int x, int y, int color) {
		if (x >= 0 && x < length && y >= 0 && y < length && getMapValue(x, y) != 0) {
			setMapValue(x, y, 0);
			if (x - 1 >= 0 && getMapValue(x - 1, y) == color) change(x - 1, y, color);
			if (y - 1 >= 0 && getMapValue(x, y - 1) == color) change(x, y - 1, color);
			if (x + 1 < length && getMapValue(x + 1, y) == color) change(x + 1, y, color);
			if (y + 1 < length && getMapValue(x, y + 1) == color) change(x, y + 1, color);
		}
	}


	public void mapAllChange() {
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < length; x++) {
				if (findParentNum(y, x) > 2) {
					if (getMapValue(x, y) != 0) change(y, x);
				}
			}
		}
	}

	public void addColor() {
		for (int i = length - 1; i >= 0; i--) {
			for (int j = 0; j < length; j++) {
				if (getMapValue(j, i) == 0) {
					restMap(j, i);
				}
			}
		}
	}

	private void swip(int x, int y, int direct) {
		if (swip(x, y, direct, true)) {
			swip(x, y, direct, false);
			mapAllChange();//��������
			moveColor();//�ƶ�����
			System.out.println();
			soutMap();
			addColor();//���
		}
		this.repaint();
	}

	public void moveColor() {
		for (int i = 0; i < length; i++) {
			moveColor(i, length - 1, length - 2);
		}
	}

	public void moveColor(int x, int y, int y1) {
		if (y <= 0) return;
		if (Map[y][x] == 0) {
			if (y1 >= 0) {
				if (Map[y1][x] == 0) {
					moveColor(x, y, y1 - 1);
				} else {
					Map[y][x] = Map[y1][x];
					Map[y1][x] = 0;
					moveColor(x, y - 1, y - 2);
				}
			}
		} else {
			moveColor(x, y - 1, y1 - 1);
		}
	}

	@Override
	public void run() {

	}

	public int getMapValue(int x, int y) {
		return Map[y][x];
	}

	public void setMapValue(int x, int y, int value) {
		Map[y][x] = value;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (Map[i][j] < 0) {
					continue;
				}
				g.setColor(colors[Map[i][j] - 1]);
				g.fill3DRect(MapLocation[i][j][0], MapLocation[i][j][1], width, height, true);
			}
		}
	}

	boolean start = true;
	int x1;
	int y1;
	int x2;
	int y2;

	void soutXY(MouseEvent e) {
		System.out.printf("X:%-3d,Y%-3d:", e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		soutXY(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		start = true;
		swip(x1, y1, x2, y2);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		if (start) {
			start = false;
			x1 = e.getX() / (width + panel_mid);
			y1 = e.getY() / (width + panel_mid);
		} else {
			x2 = e.getX() / (width + panel_mid);
			y2 = e.getY() / (width + panel_mid);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
