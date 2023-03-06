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

public class joueur implements Direction{

    public joueur(int x, int y){

    }
    
    public mouvements(Case j, Direction s) {
        switch(s) {
            case NORD:
                j.setY(getY() + 1);
                break;
            case SUD:
                j.setY(getY() - 1);
                break;
            case EST:
                j.setX(getX() + 1);
                break;
            case OUEST:
                j.setX(getX() - 1);
                break;
        }
    }
}

 
