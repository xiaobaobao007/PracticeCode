package Tools;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import javax.swing.*;

/**
 * @author xiaobaobao
 * @date 2020/8/28，15:15
 */
public class JFrameTools extends JPanel {

	public static String PATTERN = "HH:mm:ss";
	public static SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
	public static String filePath = "resources\\tool.txt";

	public static JTextArea srcText;
	public static JTextArea destText;
	public static JTextArea tipsText;
	public static JFrame jFrame;

	public static int JFRAME_WIDTH = 1640;
	public static int JFRAME_HEIGHT = 650;

	static Map<String, String> map = new HashMap<>();

	public JFrameTools() {

		jFrame = new JFrame("测试系统，最后更新时间：2020年9月4日 16点34分");
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_X = (int) (ScreenSize.getWidth() - JFRAME_WIDTH) / 2;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFRAME_HEIGHT) / 2;
		jFrame.setLocation(JFrame_X, JFrame_Y);
		jFrame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
		jFrame.setLayout(null);
		this.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		this.setLayout(null);

		srcText = new JTextArea("");
		JScrollPane scroll1 = new JScrollPane(srcText);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBounds(10, 30, 490, 400);
		this.add(scroll1);
		JLabel label1 = new JLabel("输入");
		label1.setBounds(200, 10, 100, 20);
		this.add(label1);


		destText = new JTextArea();
		JScrollPane scroll2 = new JScrollPane(destText);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(510, 30, 490, 400);
		this.add(scroll2);
		JLabel label2 = new JLabel("输出");
		label2.setBounds(700, 10, 100, 20);
		this.add(label2);

		tipsText = new JTextArea();
		JScrollPane scroll3 = new JScrollPane(tipsText);
		scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll3.setBounds(1010, 30, 600, 400);
		this.add(scroll3);
		JLabel label3 = new JLabel("操作记录");
		label3.setBounds(1300, 10, 100, 20);
		this.add(label3);

		JButton button1 = new JButton("转换【奖励】");
		button1.addActionListener((q) -> stringToItemList());

		JButton button2 = new JButton("转换【战斗属性】");
		button2.addActionListener((q) -> stringToBattleType());

		JButton button3 = new JButton("重新加载配置数据【tool.txt】");
		button3.addActionListener((q) -> reloadExcel());

		button1.setBounds(100, 450, 200, 100);
		this.add(button1);
		button2.setBounds(400, 450, 200, 100);
		this.add(button2);
		button3.setBounds(1250, 450, 350, 100);
		this.add(button3);

		reloadExcel();

		outText(tipsText, "系统成功启动有问题找包蒙阳", true, false);

		jFrame.add(this);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	public void outText(JTextArea tipsText, Exception e) {
		e.printStackTrace();
		outText(tipsText, exceptionToMessage(e), false, false);
	}

	public void outText(JTextArea tipsText, String info, boolean isSuccess, boolean isClear) {
		if (isClear) {
			tipsText.append(info);
		} else {
			tipsText.append(">>>>" + sdf.format(Calendar.getInstance().getTime()) + "<<<<");
			tipsText.append("\n");
			tipsText.append(info);
			tipsText.append("\n");
			if (isSuccess) {
				tipsText.append("↑↑↑↑↑成功↑↑↑↑↑");
			} else {
				tipsText.append("↑↑↑↑↑失败，请联系包蒙阳，并提交错误信息↑↑↑↑↑");
			}
			tipsText.append("\n");
			tipsText.append("\n");
			tipsText.selectAll();
		}
	}

	public static void main(String[] args) {
		new JFrameTools();
	}

	public static boolean isIntToString(String s) {
		if (s == null || "".equals(s)) {
			return false;
		}
		for (char c : s.toCharArray()) {
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	public void reloadExcel() {
		if (filePath == null || "".equals(filePath)) {
			outText(tipsText, "配置文件路径错误 " + filePath, false, false);
			return;
		}
		int size = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String me;
			String value;
			while ((me = in.readLine()) != null) {
				String[] split = me.split(",");
				if (split.length == 2) {
					if ((value = map.putIfAbsent(split[0], split[1])) != null && !value.equals(split[1])) {
						outText(tipsText, "配置文件信息重复：" + Arrays.toString(split), false, false);
					}
				} else {
					if ((value = map.putIfAbsent(split[0] + "_" + split[1], split[2])) != null && !value.equals(split[2])) {
						outText(tipsText, "配置文件信息重复：" + Arrays.toString(split), false, false);
					}
				}
				size++;
			}
		} catch (Exception exception) {
			outText(tipsText, exception);
		} finally {
			outText(tipsText, "成功加载了【" + size + "】数据,重复相同数据【" + (size - map.size()) + "】条", true, false);
		}
	}

	private String exceptionToMessage(Exception e) {
		StringBuilder s = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			s.append(element);
			s.append("\n");
		}
		return s.toString();
	}

	static class Item {
		final String id;
		final String num;

		public Item(String id, String num) {
			this.id = id;
			this.num = num.replace("[", "").replace("]", "");
		}

		@Override
		public String toString() {
			return "[" + id + "," + num + ']';
		}
	}

	public Item getItem(String type, String id, String num) {
		String key = id == null ? type : type + "_" + id;
		String value = map.get(key);
		if (value == null) {
			return null;
		}

		return new Item(value, num);
	}

	private boolean isError() {
		destText.setText("");
		String text = srcText.getText();
		return text == null || "".equals(text);
	}

	public void stringToItemList() {
		if (isError()) {
			return;
		}
		StringBuilder allSB = new StringBuilder();
		Item item;
		String[] split = srcText.getText().split("\n");
		int failNum = 0;
		for (String s : split) {

			StringBuilder sb = new StringBuilder("[");
			if (s == null || "".equals(s)) {
				continue;
			}

			// g   金币
			// d   钻石
			// e   经验
			// x   vip经验
			// p   道具
			// i   装备
			// c   碎片
			// s   饰品
			try {
				JSONObject jsonObject = JSONObject.parseObject(s);
				for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
					switch (entry.getKey()) {
						case "g":
						case "d":
						case "e":
						case "x":
							item = getItem(entry.getKey(), null, entry.getValue().toString());
							if (item != null) {
								sb.append(item).append(",");
							} else {
								outText(tipsText, "未匹配【" + entry.getKey() + "】【" + entry.getValue() + "】", false, false);
								failNum++;
							}
							break;
						case "p":
						case "i":
						case "c":
						case "s":
							JSONObject json = JSONObject.parseObject(entry.getValue().toString());
							for (Map.Entry<String, Object> ent : json.entrySet()) {
								item = getItem(entry.getKey(), ent.getKey(), ent.getValue().toString());
								if (item != null) {
									sb.append(item).append(",");
								} else if ("c".equals(entry.getKey())) {
									outText(tipsText, "未匹配的碎片'" + entry.getKey() + "'【" + ent.getKey() + "-" + ent.getValue() + "】", false, false);
									failNum++;
								} else {
									outText(tipsText, "未匹配'" + entry.getKey() + "'【" + ent.getKey() + "-" + ent.getValue() + "】", false, false);
									failNum++;
								}
							}
							break;
						default:
							outText(tipsText, "未匹配【" + entry.getKey() + "-" + entry.getValue() + "】", false, false);
							failNum++;
					}
				}
				if (sb.length() > 1) {
					allSB.append(sb.substring(0, sb.length() - 1)).append("]");
				}
				allSB.append("\n");
			} catch (Exception e) {
				outText(tipsText, e);
			}
		}
		outText(destText, allSB.toString().substring(0, allSB.length() - 1), true, true);
		outText(tipsText, "转换失败数【" + failNum + "】", true, false);
	}

	public void stringToBattleType() {
		if (isError()) {
			return;
		}
		StringBuilder allSB = new StringBuilder();
		Item item;
		String[] split = srcText.getText().split("\n");
		int failNum = 0;
		for (String s : split) {

			StringBuilder sb = new StringBuilder("[");
			if (s == null || "".equals(s)) {
				continue;
			}

			try {
				JSONObject jsonObject = JSONObject.parseObject(s);
				for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
					if (isIntToString(entry.getKey())) {
						float floatValue = Float.parseFloat(entry.getValue().toString());
						if (floatValue < 10.0) {
							floatValue *= 1000;
						}
						item = getItem(entry.getKey(), null, ((int) floatValue) + "");
						if (item != null) {
							sb.append(item).append(",");
						} else {
							outText(tipsText, "未匹配的战斗类型【" + entry.getKey() + "-" + entry.getValue() + "】", false, false);
							failNum++;
						}
					} else {
						outText(tipsText, "未匹配【" + entry.getKey() + "-" + entry.getValue() + "】", false, false);
						failNum++;
					}
				}
				if (sb.length() > 1) {
					allSB.append(sb.substring(0, sb.length() - 1)).append("]");
				}
				allSB.append("\n");
			} catch (Exception e) {
				outText(tipsText, e);
			}
		}
		outText(destText, allSB.toString().substring(0, allSB.length() - 1), true, true);
		outText(tipsText, "转换失败数【" + failNum + "】", true, false);
	}
}
