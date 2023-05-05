import javax.swing.*;
import java.awt.*;

public class Graphic extends JFrame implements Runnable {

    // Screen settings

    final int caseSize = 16;
    final int scale = 3;

    final int tileSize = 32 * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels

    KeyHandler keyH;
    Thread gameThread;
    JPanel gamePanel;

    public Graphic() {
        setTitle("Java Game");
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.WHITE);
                g2.fillRect(100, 100, tileSize, tileSize);
                g2.dispose();
            }
        };
        gamePanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        gamePanel.setFocusable(true);

        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyH = keyHandler;
        gamePanel.addKeyListener(keyH);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Game Loop!
        while (null != gameThread) {
            // 1 Update character position.
            update();

            // 2 Draw the screen with updated information.
            gamePanel.repaint();
            try {
                Thread.sleep(16); // Adjust the delay as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // Update game logic
    }
}
