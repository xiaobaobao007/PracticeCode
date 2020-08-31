package Tools;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
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

	public static int JFRAME_WIDTH = 1600;
	public static int JFRAME_HEIGHT = 650;

	static Map<String, String> map = new HashMap<>();

	public JFrameTools() {

		jFrame = new JFrame("测试系统");
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int JFrame_X = (int) (ScreenSize.getWidth() - JFRAME_WIDTH) / 2;
		int JFrame_Y = (int) (ScreenSize.getHeight() - JFRAME_HEIGHT) / 2;
		jFrame.setLocation(JFrame_X, JFrame_Y);
		jFrame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
		jFrame.setLayout(null);
		this.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		this.setLayout(null);

		srcText = new JTextArea("{g:1,p: {20: 11, 30: 480}}\n{g: [500], p: {10: [2]}}");
		JScrollPane scroll1 = new JScrollPane(srcText);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBounds(10, 10, 490, 400);
		this.add(scroll1);

		destText = new JTextArea();
		JScrollPane scroll2 = new JScrollPane(destText);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(510, 10, 490, 400);
		this.add(scroll2);

		tipsText = new JTextArea();
		JScrollPane scroll3 = new JScrollPane(tipsText);
		scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll3.setBounds(1010, 10, 490, 400);
		this.add(scroll3);

		JButton button1 = new JButton("转换");
		button1.addActionListener((q) -> {
			destText.setText("");

			StringBuilder allSB = new StringBuilder();
			Item item;

			String text = srcText.getText();
			String[] split = text.split("\n");
			int failNum = 0;
			for (String s : split) {

				StringBuilder sb = new StringBuilder("[");
				if (s == null || "".equals(s)) {
					continue;
				}
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
								}
								break;
							case "p":
							case "i":
								JSONObject json = JSONObject.parseObject(entry.getValue().toString());
								for (Map.Entry<String, Object> ent : json.entrySet()) {
									item = getItem(entry.getKey(), ent.getKey(), ent.getValue().toString());
									if (item != null) {
										sb.append(item).append(",");
									} else {
										outText(tipsText, "未匹配【" + ent.getKey() + "】【" + ent.getValue() + "】", false, false);
									}
								}
								break;
							default:
								outText(tipsText, "未匹配【" + entry.getKey() + "】【" + entry.getValue() + "】", false, false);
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
		});

		JButton button2 = new JButton("重新加载配置数据【tool.txt】");
		button2.addActionListener((q) -> reloadExcel());

		button1.setBounds(100, 520, 200, 50);
		this.add(button1);
		button2.setBounds(400, 520, 350, 50);
		this.add(button2);

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

	public void reloadExcel() {
		if (filePath == null || "".equals(filePath)) {
			outText(tipsText, "配置文件路径错误 " + filePath, false, false);
			return;
		}
		int size = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String me;
			while ((me = in.readLine()) != null) {
				String[] split = me.split(",");
				if (split.length == 2) {
					map.put(split[0], split[1]);
				} else {
					map.put(split[0] + "_" + split[1], split[2]);
				}
				size++;
			}
		} catch (Exception exception) {
			outText(tipsText, exception);
		} finally {
			outText(tipsText, "成功加载了" + size + "数据", true, false);
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
			outText(tipsText, "未实现类型【" + type + "】ID【" + id + "】", false, false);
			return null;
		}

		return new Item(value, num);
	}
}
