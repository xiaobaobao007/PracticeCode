package per.bmy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 鼠标跟随
 */
public class MouseInfo extends JPanel implements MouseMotionListener {

    private JLabel value_x;
    private JLabel value_y;

    private int MOUSE_X = 0;
    private int MOUSE_Y = 0;

    public static void main(String[] args) {
        new MouseInfo();
    }

    MouseInfo() {

        JFrame jFrame = new JFrame("鼠标位置");
        jFrame.setBounds(1, 8, 1900, 1000);
        this.addMouseMotionListener(this);

        this.setLayout(null);
        JLabel lblx = new JLabel("坐标x:");
        lblx.setBounds(1780, 900, 66, 31);
        this.add(lblx);

        JLabel lbly = new JLabel("坐标y:");
        lbly.setBounds(1780, 920, 66, 31);
        this.add(lbly);

        value_x = new JLabel("0");
        value_x.setBounds(1820, 900, 66, 31);
        this.add(value_x);

        value_y = new JLabel("0");
        value_y.setBounds(1820, 920, 66, 31);
        this.add(value_y);

        jFrame.add(this);

        jFrame.setVisible(true);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("*", MOUSE_X, MOUSE_Y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        value_x.setText("" + e.getX());
        value_y.setText("" + e.getY());
        MOUSE_X = e.getX();
        MOUSE_Y = e.getY();
        this.repaint();
    }

}