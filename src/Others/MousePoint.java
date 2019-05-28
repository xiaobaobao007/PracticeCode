package Others;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("unused")
public class MousePoint {

    static int command = 0;

    public MousePoint() {
        JFrame jf = new JFrame();
        jf.setUndecorated(true);
        AWTUtilities.setWindowOpacity(jf, 0.7f);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        TextField result = new TextField();
        result.setBounds(90, 950, 2000, 30);

        Label times_label = new Label("����");
        times_label.setBounds(30, 800, 30, 20);

        TextField times_text = new TextField("1");
        times_text.setBounds(30, 850, 50, 20);

        Button start = new Button("��ʼ");
        start.setBounds(30, 900, 50, 30);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        Robot robot;
                        try {
                            jf.setExtendedState(JFrame.ICONIFIED);
                            robot = new Robot();
                            int times = Integer.valueOf(times_text.getText());
                            String s = result.getText();
                            if (s.length() > 0) {
                                String[] ss = s.split(";");
                                for (int i = 0; i < times; i++) {
                                    for (int j = 0; j < ss.length; j++) {
                                        String[] sss = ss[j].split(",");
                                        int a = Integer.valueOf(sss[0]);
                                        int b = Integer.valueOf(sss[1]);
                                        int c = Integer.valueOf(sss[2]);
                                        int d = Integer.valueOf(sss[3]);
                                        Thread.sleep(d);
                                        if (c == 0) {
                                            robot.mouseMove(a, b);
                                            robot.mousePress(InputEvent.BUTTON1_MASK);
                                            robot.mouseRelease(InputEvent.BUTTON1_MASK);
                                        } else if (c == 1) {
                                            robot.mouseMove(a, b);
                                            robot.mousePress(InputEvent.BUTTON3_MASK);
                                            robot.mouseRelease(InputEvent.BUTTON3_MASK);
                                        }
                                    }
                                }
                            }
                        } catch (AWTException e1) {
                            e1.printStackTrace();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Button exit = new Button("�˳�");
        exit.setBounds(30, 950, 50, 30);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                System.exit(0);
            }
        });

        Button small = new Button("��С��");
        small.setBounds(110, 900, 50, 30);
        small.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.setExtendedState(JFrame.ICONIFIED);
            }
        });

        Button flushOne = new Button("��һ��");
        flushOne.setBounds(30, 1000, 50, 30);
        flushOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = result.getText();
                Integer a = s.lastIndexOf(";");
                if (a != -1)
                    result.setText(s.substring(0, a));
                else
                    result.setText("");
            }
        });

        Button flushAll = new Button("�������");
        flushAll.setBounds(30, 1050, 50, 30);
        flushAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                result.setText("");
            }
        });

        panel.add(result);
        panel.add(times_label);
        panel.add(times_text);
        panel.add(start);
        panel.add(small);
        panel.add(exit);
        panel.add(flushOne);
        panel.add(flushAll);
        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int a = 0;
                if (e.getButton() == MouseEvent.BUTTON1)
                    a = 0;
                else if (e.getButton() == MouseEvent.BUTTON3)
                    a = 1;
                PointerInfo pinfo = MouseInfo.getPointerInfo();
                Point p = pinfo.getLocation();
                int x = (int) p.getX();
                int y = (int) p.getY();
                String s = result.getText();
                if (s.length() == 0)
                    result.setText(x + "," + y + "," + a + ",0");
                else
                    result.setText(s + ";" + x + "," + y + "," + a + ",0");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent arg0) {

            }
        });
        jf.add(panel);
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setBounds(0, 0, (int) ScreenSize.getWidth() + 50, (int) ScreenSize.getHeight());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
//		new FileStream();
//		String a="<html>A<br>B</html>";
//		System.out.println(a.replaceAll("<br>", "!!"));
        int[][][] location = new int[2][20][2];
        int minx = 45;
        int maxx = 105;
        int miny = 20;
        int maxy = 35;
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 20; j++) {
                location[i][j][0] = random.nextInt(maxx) + minx;
                location[i][j][1] = random.nextInt(maxy) + miny;
                System.out.print(location[i][j][0] + "|" + location[i][j][1] + "/");
            }
            System.out.println();
        }


    }

    public static String qwe(String a, String b) {
        int size = a.length();
        return a.substring(0, size - 7) + "<br>" + b + "</html>";
    }
}
//ͼƬˮƽ��ת
//1399,341,1,0;1487,514,0,600;1223,587,0,0;193,110,0,800;206,235,0,600;1898,10,0,400;909,554,0,400;1516,403,0,200;1282,475,0,0;525,1061,0,0