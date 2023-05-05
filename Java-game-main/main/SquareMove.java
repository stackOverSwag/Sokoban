import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SquareMove extends JFrame implements KeyListener {
    private JPanel panel;
    private int x, y;
    
    public static void main(String[] args) {
        SquareMove move = new SquareMove();
        move.setSize(400, 400);
        move.setVisible(true);
    }
    
    public SquareMove() {
        super("Square Move");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        x = 32;
        y = 32;
        
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(x, y, 32, 32);
            }
        };
        add(panel);
        addKeyListener(this);
        setFocusable(true);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                y -= 32;
                break;
            case KeyEvent.VK_DOWN:
                y += 32;
                break;
            case KeyEvent.VK_LEFT:
                x -= 32;
                break;
            case KeyEvent.VK_RIGHT:
                x += 32;
                break;
        }
        panel.repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
}
