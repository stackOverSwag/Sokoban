import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Graphic extends JFrame implements Runnable,KeyListener {

    // Screen settings
    public boolean upPressed, downPressed, leftPressed, rightPressed, escape;
    final int scale = 3;
    final int tileSize = 32 * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels
    Matrice matrice = new Matrice("1by1_clone_flower.txt");

    KeyHandler keyH;
    Thread gameThread;
    JPanel gamePanel;

    public Graphic() {
        setTitle("Sokoban");
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        gamePanel = new JPanel() {
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gamePanel.setBackground(Color.BLACK);
                Graphics2D m = (Graphics2D) g, p=(Graphics2D) g;
                m.setColor(Color.WHITE);
                p.setColor(Color.RED);

                for(int i=0;i<matrice.getRows();i++){
                    for(int j=0;j<matrice.getCols();j++){
                        m.fillRect(i*tileSize, j*tileSize, tileSize, tileSize);
                    }
                }
                
                p.fillRect(0*tileSize, 0*tileSize, tileSize, tileSize);
                m.dispose();
                p.dispose();
            }
        };
        gamePanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);
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
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns number of key pressed
        if (code == KeyEvent.VK_ESCAPE) {
            escape = true;
        }
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        gamePanel.repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // returns number of key pressed
        if (code == KeyEvent.VK_ESCAPE) {
            escape = false;
        }
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        gamePanel.repaint();
    }
    public Direction getDirection() {
        while(!upPressed && !downPressed && !leftPressed && !rightPressed){
            if (upPressed && 
                !downPressed && 
                !leftPressed && 
                !rightPressed) {

                return Direction.NORD;
            } 

            else if (!upPressed && 
                    downPressed && 
                    !leftPressed && 
                    !rightPressed) {

                return Direction.SUD;
            } 

            else if (!upPressed && 
                    !downPressed && 
                    leftPressed && 
                    !rightPressed) {

                return Direction.OUEST;
            } 

            else if (!upPressed && 
                    !downPressed && 
                    !leftPressed && 
                    rightPressed) {

                return Direction.EST;
            }
            else{
                System.out.println("try again");
                return null;
            }
        }
        return Direction.SUD;
    }
}
