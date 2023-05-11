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
    int j=0;
    int i=0;
    Matrice matrice = new Matrice("1by1_clone_flower.txt");
    private char[][] matrix=matrice.getmatrice();
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
                
                Graphics2D b = (Graphics2D) g;
                Graphics2D p = (Graphics2D) g;
                Graphics2D c = (Graphics2D) g;
                Graphics2D m = (Graphics2D) g;
                b.setColor(Color.WHITE);
                p.setColor(Color.RED);
                c.setColor(Color.BLUE);
                m.setColor(Color.GREEN);
                for (i = 0; i < matrice.getRows();i++) {
                    for (j = 0; j < matrice.getCols(); j++) {
                        if (Character.isUpperCase(matrix[i][j]) && matrix[i][j] != 'A') {
                            Graphics2D bg = (Graphics2D) g.create();
                            bg.setColor(Color.WHITE);
                            bg.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                            bg.dispose();
                        } else if (matrix[i][j] == 'A') {
                            Graphics2D pg = (Graphics2D) g.create();
                            pg.setColor(Color.RED);
                            pg.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                            pg.dispose();
                        } else if (matrix[i][j] == '@') {
                            Graphics2D cg = (Graphics2D) g.create();
                            cg.setColor(Color.BLUE);
                            cg.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                            cg.dispose();
                        } else if (matrix[i][j] == '#') {
                            Graphics2D mg = (Graphics2D) g.create();
                            mg.setColor(Color.GREEN);
                            mg.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                            mg.dispose();
                        }
                    }
                }
                
                
                
                
            }
        };
        gamePanel.setBackground(Color.BLACK);
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
