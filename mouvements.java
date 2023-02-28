/*
 * * Objectif mouvements:
 * modifier cordonnées joueur et boite (x,y)
 * aller sur une case designé efficacement si possible
 * sinon rien (car boite sur le chemin ou bloqué)
 * methodes:
 *      +estlibre(p,d) pour savoir si le joueur p 
 *      peut se deplacer dans la direction d sanq bouger de caisse
 *      +suivant(p,d) modifie position de p si estlibre vrai

 */
import Case.java;
import direction.java;

public class mouvements implements direction{
    
    public mouvements(Case p, direction s) {
        switch(s) {
            case NORD:
                p.setY(getY() + 1);
                break;
            case SUD:
                p.setY(getY() - 1);
                break;
            case EST:
                p.setX(getX() + 1);
                break;
            case OUEST:
                p.setX(getX() - 1);
                break;
        }
    }
}

 
