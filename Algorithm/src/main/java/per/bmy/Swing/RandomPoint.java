package per.bmy.Swing;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.commons.lang.math.RandomUtils;

public class RandomPoint extends JPanel {

    public static JFrame jFrame;
    public static int JFRAME_WIDTH = 1300;
    public static int JFRAME_HEIGHT = 950;
    static boolean first = true;
    int[][] map = {{-1561, 162}, {-891, 619}, {-351, 277}, {-1013, -195}};

    public RandomPoint() {

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

                this.repaint();
            }
        }).start();
    }

    public static void main(String[] args) {
        new RandomPoint();
    }

    @Override
    public void paint(Graphics g) {
        int M = 1600;
        int N = 200;

        if (first) {
            g.setColor(Color.GREEN);
            ((Graphics2D) g).setStroke(new BasicStroke(5.0f));
            for (int i = 0, j = 3; i < 4; j = i++) {
                g.drawLine(map[i][0] + M, map[i][1] + N, map[j][0] + M, map[j][1] + N);
            }
            first = false;
        }
        g.setColor(Color.RED);

        int w = RandomUtils.nextInt(JFRAME_WIDTH);
        int h = RandomUtils.nextInt(JFRAME_HEIGHT);

        while (!map_collision(w - M, h - N)) {
            w = RandomUtils.nextInt(JFRAME_WIDTH);
            h = RandomUtils.nextInt(JFRAME_HEIGHT);
        }
        g.fillOval(w, h, 20, 20);

    }

    public boolean map_collision(float testx, float testy) {
        int[][] ver = map;
        int nvert = map.length;
        int i, j;
        boolean c = false;
        for (i = 0, j = nvert - 1; i < nvert; j = i++) {
            if (((ver[i][1] > testy) != (ver[j][1] > testy)) && (testx < (ver[j][0] - ver[i][0]) * (testy - ver[i][1]) / (ver[j][1] - ver[i][1]) + ver[i][0]))
                c = !c;
        }
        return c;
    }

}
