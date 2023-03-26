import javax.swing.*;
import java.awt.*;

public class Graphic extends JFrame implements Runnable {

    // Screen settings

    final int caseSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread();

    public Graphic() {

        this.setTitle("Java Game");
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);

    }

    @Override
    public void run() { // Game Loop!

        while(null != gameThread) {

            // 1 Update character position.
            update();

            // 2 Draw the screen with updated information.
            repaint();

        }

    }

    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); // used whenever paintComponent is used.

        Graphics2D g2 = (Graphics2D) g; // A CAST?!?!? (I failed OOP)
                                        // Graphics2D has some functions we'll need later :)

        g2.setColor(Color.white); // Our object
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose(); // Memory saving technique
    }

}
