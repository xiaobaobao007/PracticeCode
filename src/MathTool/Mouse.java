package MathTool;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author xiaobaobao
 * @date 2019/11/4，13:32
 */
public class Mouse extends JPanel implements MouseMotionListener, MouseListener {
	public static Robot robot;


	public static void main(String[] args) {

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		JFrame jFrame = new JFrame("测试系统");
		jFrame.setLocation(1500, 300);
		jFrame.setSize(300, 300);
		JButton button = new JButton("邮件");
		button.setSize(300,300);
		button.addActionListener((q) -> {
			doit();
		});
		jFrame.add(button);
		jFrame.setVisible(true);
	}


	public static void doit() {
		robot.mouseMove(1120, 278);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_DOWN);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.mouseMove(1440, 305);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.mouseMove(1600	, 500);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
