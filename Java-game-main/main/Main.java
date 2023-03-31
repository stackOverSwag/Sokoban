import javax.lang.model.element.Element;
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
        while(!victory(matrice)){
           suivant(p,s,matrice);
        }
        System.out.println("GG well play you are a Top G");

    }
    public boolean suivant(Joueur p, Direction s, Matrice matrice) {
        if(estlibre(p,s,matrice)){
            switch(s) {
            case NORD:
                if(matrice.getElement(p.getX(),p.getY()+1)=='#') return false;

                if(matrice.getElement(p.getX(),p.getY()+1)=='@')
                    matrice.setElement(p.getX(),p.getY()+1,'a');
                else
                    matrice.setElement(p.getX(),p.getY()+1,'A');

                    if(matrice.getElement(p.getX(),p.getY())=='a')
                    matrice.setElement(p.getX(),p.getY(),'@');
                else
                    matrice.setElement(p.getX(),p.getY(),' ');
                p.setY(p.getY() + 1);
                break;

            case SUD:
                if(matrice.getElement(p.getX(),p.getY()-1)=='#') return false;

                if(matrice.getElement(p.getX(),p.getY()-1)=='@')
                    matrice.setElement(p.getX(),p.getY()-1,'a');
                else
                    matrice.setElement(p.getX(),p.getY()-1,'A');

                    if(matrice.getElement(p.getX(),p.getY())=='a')
                    matrice.setElement(p.getX(),p.getY(),'@');
                else
                    matrice.setElement(p.getX(),p.getY(),' ');
                p.setY(p.getY() - 1);
                break;

            case EST:
                if(matrice.getElement(p.getX()+1,p.getY())=='#') return false;

                if(matrice.getElement(p.getX()+1,p.getY())=='@')
                    matrice.setElement(p.getX()+1,p.getY(),'a');
                else
                    matrice.setElement(p.getX()+1,p.getY(),'A');

                    if(matrice.getElement(p.getX(),p.getY())=='a')
                    matrice.setElement(p.getX(),p.getY(),'@');
                else
                    matrice.setElement(p.getX(),p.getY(),' ');    
                p.setX(p.getX() + 1);
                break;

            case OUEST:
                if(matrice.getElement(p.getX()-1,p.getY())=='#') return false;

                if(matrice.getElement(p.getX()-1,p.getY())=='@')
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
        return false;
        
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

    /*public boolean victory(Matrice matrice){
        for(Element e in matrice){}
    }
    je m'en occuperais
    */

}

