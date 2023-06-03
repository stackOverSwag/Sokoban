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
                s=keyH.getDirection();
                //while(s== Direction.NONE){s=keyH.getDirection();};
                System.out.println(matrice.toString());
                System.out.println("yes");
                try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
                suivant(p, s, matrice);
                gameWon = keyH.Pause();
                gamePaused = false;
            }

            if (gameWon) {
                System.out.println("GG well played! You are a Top G");
                
                try {
                    Thread.sleep(500); // Adjust the delay as needed
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
    
    public static boolean suivant(Joueur p, Direction s, Matrice matrice) {
        int n=1;
        if (estlibre(p, s, matrice)) {
            try { Thread.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
            switch (s) {
                case EST:
                    if (matrice.getElement(p.getX(), p.getY() + 1) == '#') return false;

                    if (matrice.getElement(p.getX(), p.getY() + 1) == '@')
                        matrice.setElement(p.getX(), p.getY() + 1, 'a');
                    else
                        matrice.setElement(p.getX(), p.getY() + 1, 'A');

                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');

                    p.setY(p.getY() + 1);
                    break;

                case OUEST:
                    if (matrice.getElement(p.getX(), p.getY() - 1) == '#') return false;

                    if (matrice.getElement(p.getX(), p.getY() - 1) == '@')
                        matrice.setElement(p.getX(), p.getY() - 1, 'a');
                    else
                        matrice.setElement(p.getX(), p.getY() - 1, 'A');

                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');
                    p.setY(p.getY() - 1);
                    break;

                case SUD:
                    if (matrice.getElement(p.getX() + 1, p.getY()) == '#') return false;

                    if (matrice.getElement(p.getX() + 1, p.getY()) == '@')
                        matrice.setElement(p.getX() + 1, p.getY(), 'a');
                    else
                        matrice.setElement(p.getX() + 1, p.getY(), 'A');

                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');
                    p.setX(p.getX() + 1);
                    break;

                case NORD:
                    if (matrice.getElement(p.getX() - 1, p.getY()) == '#') return false;

                    if (matrice.getElement(p.getX() - 1, p.getY())=='@')
                        matrice.setElement(p.getX()-1,p.getY(),'a');
                    else
                        matrice.setElement(p.getX()-1,p.getY(),'A');

                    if(matrice.getElement(p.getX(),p.getY())=='a')
                        matrice.setElement(p.getX(),p.getY(),'@');
                    else
                        matrice.setElement(p.getX(),p.getY(),' ');
                    p.setX(p.getX() - 1);
                    break;
            }
            
        }
        /*else{
            for(;;){
                n++;
                switch (s) {
                case EST:
                    if (matrice.getElement(p.getX(), p.getY() + n) == '#') return false;
                    if (Character.isUpperCase(matrice.getElement(p.getX(),p.getY()+1)) && matrice.getElement(p.getX(),p.getY()+1) != 'A') break;
                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');
                    matrice.setElement(p.getX(), p.getY() + 1, 'A');
                    p.setY(p.getY() + 1);
                    matrice.setElement(p.getX(), p.getY() + n, matrice.getElement(p.getX(), p.getY()+n-1));
                    return true;
                    

                case OUEST:
                    if (matrice.getElement(p.getX(), p.getY() - n) == '#') return false;
                    if (Character.isUpperCase(matrice.getElement(p.getX(),p.getY()-1)) && matrice.getElement(p.getX(),p.getY()-1) != 'A') break;
                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');
                    matrice.setElement(p.getX(), p.getY() - 1, 'A');
                    p.setY(p.getY() - 1);
                    matrice.setElement(p.getX(), p.getY() - n, matrice.getElement(p.getX(), p.getY()-n+1));
                    return true;

                case NORD:
                    if (matrice.getElement(p.getX()+n, p.getY() ) == '#') return false;
                    if (Character.isUpperCase(matrice.getElement(p.getX()+1,p.getY())) && matrice.getElement(p.getX()+1,p.getY()) != 'A') break;
                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');
                    matrice.setElement(p.getX()+1, p.getY() , 'A');
                    p.setX(p.getX() + 1);
                    matrice.setElement(p.getX(), p.getY() + n, matrice.getElement(p.getX()+n-1, p.getY()));
                    return true;

                case SUD:
                    if (matrice.getElement(p.getX()-n, p.getY()) == '#') return false;
                    if (Character.isUpperCase(matrice.getElement(p.getX()-1,p.getY())) && matrice.getElement(p.getX()-1,p.getY()) != 'A') break;
                    if (matrice.getElement(p.getX(), p.getY()) == 'a')
                        matrice.setElement(p.getX(), p.getY(), '@');
                    else
                        matrice.setElement(p.getX(), p.getY(), ' ');
                    matrice.setElement(p.getX()-1, p.getY(), 'A');
                    p.setX(p.getX() -1);
                    matrice.setElement(p.getX()-n, p.getY() , matrice.getElement(p.getX()-n+1, p.getY()));
                    return true;
                }
            }
        }*/
        return true;
    }
    
    public static boolean estlibre(Joueur p, Direction s, Matrice matrice) {
        switch(s) {
            case EST: 
                if(Character.isUpperCase(matrice.getElement(p.getX(),p.getY()+1)) && matrice.getElement(p.getX(),p.getY()+1) != 'A') return false;
                break;
            case OUEST:
                if(Character.isUpperCase(matrice.getElement(p.getX(),p.getY()-1)) && matrice.getElement(p.getX(),p.getY()+1) != 'A') return false;
                break;
            case NORD:
                if(Character.isUpperCase(matrice.getElement(p.getX()+1,p.getY())) && matrice.getElement(p.getX(),p.getY()+1) != 'A') return false;
                break;
            case SUD:
                if(Character.isUpperCase(matrice.getElement(p.getX()-1,p.getY())) && matrice.getElement(p.getX(),p.getY()+1) != 'A') return false;
                break;
            case NONE: 
                return false;
        }
        return true;
    }

    

    public static boolean victory(Matrice matrice){
        for(int i=0;i<matrice.getRows();i++){
            for(int j=0;j<matrice.getCols();j++){
            if(matrice.getElement(i, j)=='@') return false;
            }
        }
        return true;
    }
  



    
    
}