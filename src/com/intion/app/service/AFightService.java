package com.intion.app.service;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.alibaba.fastjson.JSON;
import com.intion.app.fight.Battle;
import com.intion.app.fight.constant.FightConstant;
import com.intion.app.fight.domain.FightUnit;

import javax.swing.*;

public class AFightService extends JPanel implements MouseListener {

	public static AFightService me;

	public Battle battle;

	JTextField aUId;//aUId
	JTextField aAtkId;//aAtkId
	JTextField aDefId;//aDefId
	JTextField aXX;//aXX
	JTextField aYY;//aYY
	JTextField dUId;//dUId
	JTextField dAtkId;//dAtkId
	JTextField dDefId;//dDefId
	JTextField dXX;//dXX
	JTextField dYY;//dYY

	int JFRAME_WIDTH = 1400;
	int JFRAME_HEIGHT = 800;

	int MAP_STARTX = 500;
	int MAP_STARTY = 50;

	int CUBE_SIZE = 120;
	int CUBE_SPACE = 15;

	public void init() {
		JFrame jFrame = new JFrame("测试系统");
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_X = (int) (ScreenSize.getWidth() - JFRAME_WIDTH) / 2;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFRAME_HEIGHT) / 2;
		jFrame.setLocation(JFrame_X, JFrame_Y);
		jFrame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
		jFrame.setLayout(null);
		this.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		this.setLayout(null);

		int x = 60;
		int y = 20;

		JLabel aUId = new JLabel("aUId");
		aUId.setFont(new Font("宋体", Font.BOLD, 15));
		aUId.setBounds(x - 50, y - 13, 200, 50);
		this.aUId = new JTextField("1");
		this.aUId.setBounds(x + 40, y, 90, 30);
		this.add(aUId);
		this.add(this.aUId);

		x = 60;
		y = 80;
		JLabel aAtkId = new JLabel("aAtkId");
		aAtkId.setFont(new Font("宋体", Font.BOLD, 15));
		aAtkId.setBounds(x - 50, y - 13, 200, 50);
		this.aAtkId = new JTextField("3");
		this.aAtkId.setBounds(x + 40, y, 90, 30);
		this.add(aAtkId);
		this.add(this.aAtkId);

		x = 60;
		y = 140;
		JLabel aDefId = new JLabel("aDefId");
		aDefId.setFont(new Font("宋体", Font.BOLD, 15));
		aDefId.setBounds(x - 50, y - 13, 200, 50);
		this.aDefId = new JTextField("6");
		this.aDefId.setBounds(x + 40, y, 90, 30);
		this.add(aDefId);
		this.add(this.aDefId);

		x = 60;
		y = 200;
		JLabel aXX = new JLabel("aXX");
		aXX.setFont(new Font("宋体", Font.BOLD, 15));
		aXX.setBounds(x - 50, y - 13, 200, 50);
		this.aXX = new JTextField("2");
		this.aXX.setBounds(x + 40, y, 90, 30);
		this.add(aXX);
		this.add(this.aXX);

		x = 60;
		y = 260;
		JLabel aYY = new JLabel("aYY");
		aYY.setFont(new Font("宋体", Font.BOLD, 15));
		aYY.setBounds(x - 50, y - 13, 200, 50);
		this.aYY = new JTextField("2");
		this.aYY.setBounds(x + 40, y, 90, 30);
		this.add(aYY);
		this.add(this.aYY);

		x = 250;
		y = 20;
		JLabel dUId = new JLabel("dUId");
		dUId.setFont(new Font("宋体", Font.BOLD, 15));
		dUId.setBounds(x - 50, y - 13, 200, 50);
		this.dUId = new JTextField("2");
		this.dUId.setBounds(x + 40, y, 90, 30);
		this.add(dUId);
		this.add(this.dUId);

		x = 250;
		y = 80;
		JLabel dAtkId = new JLabel("dAtkId");
		dAtkId.setFont(new Font("宋体", Font.BOLD, 15));
		dAtkId.setBounds(x - 50, y - 13, 200, 50);
		this.dAtkId = new JTextField("8");
		this.dAtkId.setBounds(x + 40, y, 90, 30);
		this.add(dAtkId);
		this.add(this.dAtkId);

		x = 250;
		y = 140;
		JLabel dDefId = new JLabel("dDefId");
		dDefId.setFont(new Font("宋体", Font.BOLD, 15));
		dDefId.setBounds(x - 50, y - 13, 200, 50);
		this.dDefId = new JTextField("3");
		this.dDefId.setBounds(x + 40, y, 90, 30);
		this.add(dDefId);
		this.add(this.dDefId);

		x = 250;
		y = 200;
		JLabel dXX = new JLabel("dXX");
		dXX.setFont(new Font("宋体", Font.BOLD, 15));
		dXX.setBounds(x - 50, y - 13, 200, 50);
		this.dXX = new JTextField("2");
		this.dXX.setBounds(x + 40, y, 90, 30);
		this.add(dXX);
		this.add(this.dXX);

		x = 250;
		y = 260;
		JLabel dYY = new JLabel("dYY");
		dYY.setFont(new Font("宋体", Font.BOLD, 15));
		dYY.setBounds(x - 50, y - 13, 200, 50);
		this.dYY = new JTextField("3");
		this.dYY.setBounds(x + 40, y, 90, 30);
		this.add(dYY);
		this.add(this.dYY);

		JButton button = new JButton("待命");
		button.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getAUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_NOTHING, 0, 0, 0, 0, 0)));
			this.repaint();
		});

		JButton button1 = new JButton("攻击");
		button1.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getAUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_ATTACK, getAAtkId(), getADefId(), getAXX(), getAYY(), 0)));
			this.repaint();
		});

		JButton button2 = new JButton("移动");
		button2.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getAUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_MOVE, getAAtkId(), getADefId(), getAXX(), getAYY(), 0)));
			this.repaint();
		});

		JButton button3 = new JButton("战斗播放完成");
		button3.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getAUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_SHOW_OVER, 0, 0, 0, 0, 0)));
			this.repaint();
		});

		JButton button4 = new JButton("");
		button4.addActionListener((q) -> {

		});

		JButton button5 = new JButton("待命");
		button5.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getDUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_NOTHING, 0, 0, 0, 0, 0)));
			this.repaint();
		});

		JButton button6 = new JButton("攻击");
		button6.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getDUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_ATTACK, getDAtkId(), getDDefId(), getDXX(), getDYY(), 0)));
			this.repaint();
		});

		JButton button7 = new JButton("移动");
		button7.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getDUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_MOVE, getDAtkId(), getDDefId(), getDXX(), getDYY(), 0)));
			this.repaint();
		});

		JButton button8 = new JButton("战斗播放完成");
		button8.addActionListener((q) -> {
			System.out.println("操作结果：\n" + JSON.toJSONString(battle.playerOperation(getDUId(), FightConstant.FIGHT_PLAYER_OPERATION_TYPE_SHOW_OVER, 0, 0, 0, 0, 0)));
			this.repaint();
		});

		JButton button9 = new JButton("清除战斗卡死");
		button9.addActionListener((q) -> {
			try {
				battle.clearCB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		button.setBounds(50, 300, 145, 50);
		this.add(button);
		button1.setBounds(50, 360, 145, 50);
		this.add(button1);
		button2.setBounds(50, 420, 145, 50);
		this.add(button2);
		button3.setBounds(50, 480, 145, 50);
		this.add(button3);
		button4.setBounds(50, 540, 145, 50);
		this.add(button4);
		button5.setBounds(220, 300, 145, 50);
		this.add(button5);
		button6.setBounds(220, 360, 145, 50);
		this.add(button6);
		button7.setBounds(220, 420, 145, 50);
		this.add(button7);
		button8.setBounds(220, 480, 145, 50);
		this.add(button8);
		button9.setBounds(220, 540, 145, 50);
		this.add(button9);

		jFrame.add(this);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*jFrame.addMouseMotionListener(this);
		jFrame.addMouseListener(this);*/
		jFrame.setVisible(true);

		me = this;
	}

	public static void main(String[] args) {
		// new ASendEmailService().init();
	}


	private int getAUId() {
		return Integer.parseInt(aUId.getText());
	}

	private int getAAtkId() {
		return Integer.parseInt(aAtkId.getText());
	}

	private int getADefId() {
		return Integer.parseInt(aDefId.getText());
	}

	private int getAXX() {
		return Integer.parseInt(aXX.getText());
	}

	private int getAYY() {
		return Integer.parseInt(aYY.getText());
	}

	private int getDUId() {
		return Integer.parseInt(dUId.getText());
	}

	private int getDAtkId() {
		return Integer.parseInt(dAtkId.getText());
	}

	private int getDDefId() {
		return Integer.parseInt(dDefId.getText());
	}

	private int getDXX() {
		return Integer.parseInt(dXX.getText());
	}

	private int getDYY() {
		return Integer.parseInt(dYY.getText());
	}

	@Override
	public void paint(Graphics g) {
		if (battle == null) {
			return;
		}
		super.paint(g);
		// g.setFont(new Font("宋体", Font.BOLD, 15));
		for (int y = 0; y < FightConstant.AREA_HEIGHT; y++) {
			for (int x = 0; x < FightConstant.AREA_WIDTH; x++) {

				int paintX = MAP_STARTX + x * (CUBE_SIZE + CUBE_SPACE);
				int paintY = MAP_STARTY + y * (CUBE_SIZE + CUBE_SPACE);

				g.setColor(Color.BLACK);
				g.drawRect(paintX, paintY, CUBE_SIZE, CUBE_SIZE);
				g.drawString("y：" + y + "   x：" + x, paintX + 5, paintY - 3);
				paintOneUnit(g, x, y, paintX + 5, paintY + 2);
			}
		}
	}

	private void paintOneUnit(Graphics g, int x, int y, int paintX, int paintY) {
		FightUnit fightUnit = battle.area.areaMap[y][x];
		if (fightUnit == null || !fightUnit.alive) {
			return;
		}
		if (fightUnit.isAttack) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLUE);
		}
		g.drawString("id：" + fightUnit.userId + "-" + fightUnit.heroId, paintX, paintY + 10);
		g.drawString("血：" + fightUnit.hp + "/" + fightUnit.property.maxHp, paintX, paintY + 30);
		g.drawString("攻击：" + fightUnit.property.getPhyAtk(), paintX, paintY + 50);
		g.drawString("防御：" + fightUnit.property.getPhyDef(), paintX, paintY + 70);
		g.drawString("移动：" + fightUnit.heroConfig.getMove(), paintX, paintY + 90);
		g.drawString("手长：" + fightUnit.heroConfig.getDistance(), paintX, paintY + 110);
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
}
