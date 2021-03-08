import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class DrawPanel extends JPanel implements MouseListener{
        public DrawPanel() {
                addMouseListener(this);
        }
        ArrayList<Point> list = new ArrayList<Point>();
        @Override
        public Dimension getPreferredSize() {
                return new Dimension(400, 300);
        }
        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Point pt : list){
                        g.drawRect(pt.x,pt.y,5,5);
                }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
                list.add(new Point(e.getX(),e.getY()));
                repaint();
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        public static void main(String[] args) {
                JFrame frame = new JFrame("test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new DrawPanel());
                frame.pack();
                frame.setVisible(true);
        }
}