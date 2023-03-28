import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {

    public static void main (String[] args) {
        
        /*JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread(); // starts the game!
        */
        //en attendant que ce soit operationel
        
        
        
    
        Matrice matrice = new Matrice("1by1_clone_flower.txt");
        System.out.println(matrice.toString());

    }
    public void suivant(Joueur p, Direction s, Matrice matrice) {
        if(estlibre(p,s,matrice)){
            switch(s) {
            case NORD:
                if(matrice.getElement(p.getX(),p.getY()+1)=='#') break;
                p.setY(p.getY() + 1);
                break;
            case SUD:
                if(matrice.getElement(p.getX(),p.getY()-1)=='#') break;
                p.setY(p.getY() - 1);
                break;
            case EST:
                if(matrice.getElement(p.getX()+1,p.getY())=='#') break;
                p.setX(p.getX() + 1);
                break;
            case OUEST:
                if(matrice.getElement(p.getX()-1,p.getY())=='#') break;
                p.setX(p.getX() - 1);
                break;
            }
        }
        
    }
    
    public boolean estlibre(Joueur p, Direction s, Matrice matrice) {
        switch(s) {
            case NORD:
                if(matrice.getElement(p.getX(),p.getY()+1)=='B') return false;
            case SUD:
                if(matrice.getElement(p.getX(),p.getY()-1)=='B') return false;
            case EST:
                if(matrice.getElement(p.getX()+1,p.getY())=='B') return false;
            case OUEST:
                if(matrice.getElement(p.getX()-1,p.getY())=='B') return false;
        }
        return true;
    }
}

/*it does not recognize Gamepanel class */