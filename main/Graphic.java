import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Graphic extends JFrame implements Runnable {

    // Screen settings
    final int scale = 5;
    final int tileSize = 16 * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels
    int j = 0;
    int i = 0;
    int cpt = 0;
    Thread gameThread;
    JPanel gamePanel;
    String[] fileNames;
    private BufferedImage blockImage;
    private BufferedImage playerImage;
    private BufferedImage targetImage;
    private BufferedImage wallImage;
    private BufferedImage victoryImage;
    private BufferedImage champImage;
    public Graphic() {

        String directoryPath = "niveaux/";
        // Create a File object for the directory
        File directory = new File(directoryPath);
        // Get all files in the directory
        File[] files = directory.listFiles();
        // Check if the directory exists
        if (directory.exists()) {
            // Check if it's a directory
            if (directory.isDirectory()) {
                // Check if any files exist in the directory
                if (files != null && files.length > 0) {
                    // Print the filenames and store them in an array
                    fileNames = new String[files.length];
                    for (cpt = 0; cpt < files.length; cpt++) {
                        File file = files[cpt];
                        String fileName = file.getName();
                        if (fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("txt")) {
                            fileNames[cpt] = fileName;
                        }
                    }
                    // Use the fileNames array as needed
                } else {
                    System.out.println("No files found in the directory.");
                }
            } else {
                System.out.println(directoryPath + " is not a directory.");
            }
        } else {
            System.out.println(directoryPath + " does not exist.");
        }
        setTitle("Sokoban");
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        cpt = 0;
        try {
            blockImage = loadImage("images/boite.png");
            playerImage = loadImage("images/mario.png");
            targetImage = loadImage("images/cible.png");
            wallImage = loadImage("images/mur.png");
            victoryImage = loadImage("images/victory.jpg");
            champImage = loadImage("images/champ.png");
            // Use the loaded images as needed
        } catch (IOException e) {
            e.printStackTrace();
        }

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Matrice matrice = new Matrice("niveaux/" + fileNames[cpt]);
                char[][] matrix = matrice.getmatrice();

                for (i = 0; i < matrice.getRows(); i++) {
                    for (j = 0; j < matrice.getCols(); j++) {
                        int x = j * tileSize;
                        int y = i * tileSize;

                        if (Character.isUpperCase(matrix[i][j]) && matrix[i][j] != 'A') {
                            // Draw the loaded image for uppercase blocks
                            g.drawImage(blockImage, x, y, tileSize, tileSize, this);
                        } else if (matrix[i][j] == 'A') {
                            // Draw the loaded image for player block
                            g.drawImage(playerImage, x, y, tileSize, tileSize, this);
                        }
                        else if (matrix[i][j] == 'a') {
                            // Draw the loaded image for player block
                            g.drawImage(victoryImage, x, y, tileSize, tileSize, this);
                        }
                        else if (matrix[i][j] == 'b') {
                            // Draw the loaded image for player block
                            g.drawImage(champImage, x, y, tileSize, tileSize, this);
                        } else if (matrix[i][j] == '@') {
                            // Draw the loaded image for target block
                            g.drawImage(targetImage, x, y, tileSize, tileSize, this);
                        } else if (matrix[i][j] == '#') {
                            // Draw the loaded image for wall block
                            g.drawImage(wallImage, x, y, tileSize, tileSize, this);
                        }
                    }
                }
            }
        };
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        gamePanel.setFocusable(true);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
    }

    public String fileName() {
        return fileNames[cpt];
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Game Loop!
        while (null != gameThread) {
            // 1 Update character position.
            // 2 Draw the screen with updated information.

            try {
                Thread.sleep(2000); // Adjust the delay as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gamePanel.repaint();
        }
    }

    public void update() {
        cpt++;
        // Update game logic
    }

    private BufferedImage loadImage(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int desiredWidth = tileSize;
        int desiredHeight = tileSize;

        if (imageWidth != desiredWidth || imageHeight != desiredHeight) {
            Image resizedImage = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_DEFAULT);
            BufferedImage resizedBufferedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedBufferedImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();
            return resizedBufferedImage;
        } else {
            return image;
        }
    }
}
