import javax.swing.*;
import java.util.List;
import java.awt.Color;
import java.io.File;



public class Main {
    public static void main (String[] args) {

        Graphic game = new Graphic();
        KeyHandler keyH = new KeyHandler();
        game.setKeyHandler(keyH);
        game.setVisible(true);
        game.setTitle("Sokoban");
        game.setResizable(false);
        game.pack();
        game.setLocationRelativeTo(null);
        game.startGameThread(); // starts the game!


        Matrice matrice = new Matrice("1by1_clone_flower.txt");
        Joueur p = matrice.getJoueurs().get(0);
        game.addKeyListener(keyH); // Add KeyHandler to the game
        game.setFocusable(true);

        boolean gameWon = false;
        boolean gamePaused = false;

        while (!gameWon && !gamePaused) {
            Direction s = keyH.getDirection();
            suivant(p, s, matrice);
            gameWon = victory(matrice);
            gamePaused = keyH.Pause();
        }

        if (gameWon) {
            System.out.println("GG well played! You are a Top G");
        } else if (gamePaused) {
            System.out.println("Game paused.");
        } else {
            System.out.println("Loser! Try again.");
        }
    }
    
    public static boolean suivant(Joueur p, Direction s, Matrice matrice) {
        if (estlibre(p, s, matrice)) {
            switch (s) {
                case NORD:
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

                case SUD:
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

                case EST:
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

                case OUEST:
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
            matrice.toString();
            return true;
        }

        else
        return false;
        
    }
    
    public static boolean estlibre(Joueur p, Direction s, Matrice matrice) {
        switch(s) {
            case NORD:
                if(matrice.getElement(p.getX(),p.getY()+1)=='B') return false;
                break;
            case SUD:
                if(matrice.getElement(p.getX(),p.getY()-1)=='B') return false;
                break;
            case EST:
                if(matrice.getElement(p.getX()+1,p.getY())=='B') return false;
                break;
            case OUEST:
                if(matrice.getElement(p.getX()-1,p.getY())=='B') return false;
                break;
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

