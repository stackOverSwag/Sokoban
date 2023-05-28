import javax.swing.*;
import java.util.List;
import java.awt.Color;
import java.io.File;



public class Main {
    public static void main (String[] args) {

        int i=0, cpt=0;
        KeyHandler keyH = new KeyHandler();
        String directoryPath = "niveaux/";
        // Create a File object for the directory
        File directory = new File(directoryPath);
        // Get all files in the directory
        File[] files = directory.listFiles();
        // Check if the directory exists
        String[] fileNames={};
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
        cpt=0;

        Matrice matrice = new Matrice("niveaux/" + fileNames[cpt]);
        Joueur p=matrice.getJoueurs().get(0);
        matrice.getmatrice();
        Graphic game = new Graphic(matrice);
        game.setVisible(true);
        game.setTitle("Sokoban");
        game.setResizable(false);
        game.pack();
        game.setLocationRelativeTo(null);
        game.startGameThread(); // starts the game!
        game.addKeyListener(keyH); // Add KeyHandler to the game
        game.setFocusable(true); 
        boolean gameWon = false;
        boolean gamePaused = false;
        Direction s;
        
        for(;;){
            while (!gameWon && !gamePaused) {
                s = keyH.getDirection();
                //while(s== Direction.NONE){};
                
                System.out.println(matrice.toString());
                System.out.println("yes");
                try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
                suivant(p, s, matrice);
                game.setMatrice(matrice);
                gameWon = keyH.Pause();
                gamePaused = false;
            }

            if (gameWon) {
                System.out.println("GG well played! You are a Top G");
                
                try {
                    Thread.sleep(2000); // Adjust the delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cpt++;
                matrice = new Matrice("niveaux/" + fileNames[cpt]);
                matrice.getmatrice();
                p=matrice.getJoueurs().get(0);
                game.setMatrice(matrice);
                gameWon=false;
                
            }
            else if (gamePaused) {
                System.out.println("Game paused.");
                try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
                gamePaused=false;
            } 
        }
    
    }
