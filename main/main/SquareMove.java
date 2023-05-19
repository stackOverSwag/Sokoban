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
         // Screen settings

        final int scale = 3;

        final int tileSize = 32 * scale; // 48x48 tile
        final int maxScreenCol = 16;
        final int maxScreenRow = 16;
        final int screenWidth = tileSize * maxScreenCol; // 768 pixels
        final int screenHeight = tileSize * maxScreenRow; // 768 pixels

        setTitle("Sokoban");
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

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
            case KeyEvent.VK_Z:
                y -= 32;
                break;
            case KeyEvent.VK_S:
                y += 32;
                break;
            case KeyEvent.VK_Q:
                x -= 32;
                break;
            case KeyEvent.VK_D:
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
