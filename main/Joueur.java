	/*
 * * Objectif mouvements:
 * modifier cordonnées joueur et boite (x,y)
 * aller sur une case designé efficacement si possible
 * sinon rien (car boite sur le chemin ou bloqué)
 * methodes:
 *      +estlibre(p,d) pour savoir si le joueur p 
 *      peut se deplacer dans la direction d sanq bouger de caisse
 *      +suivant(p,d) modifie position de p si estlibre true
 */

 import Matrice.java;


public class Joueur extends Case implements Direction{
    private int x, y;
    public Joueur(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void suivant(Case j, Direction s, Matrice m) {
        if(estlibre){
            switch(s) {
            case NORD:
                if(matrix[getX()][getY() + 1]=='#') break;
                j.setY(getY() + 1);
                break;
            case SUD:
                if(matrix[getX()][getY() - 1]=='#') break;
                j.setY(getY() - 1);
                break;
            case EST:
                if(matrix[getX() + 1][getY()]=='#') break;
                j.setX(getX() + 1);
                break;
            case OUEST:
                if(matrix[getX() - 1][getY()]=='#') break;
                j.setX(getX() - 1);
                break;
            }
        }
        
    }
    public boolean estlibre(Case j, Direction s) {
        switch(s) {
            case NORD:
                if(matrix[getX()][getY() + 1]=='B') return false;
            case SUD:
                if(matrix[getX()][getY() - 1]=='B') return false;
            case EST:
                if(matrix[getX() + 1][getY()]=='B') return false;
            case OUEST:
                if(matrix[getX() - 1][getY()]=='B') return false;
        }
        return true;
    }
    public void fin(Case j, Case c){
        if(j.getX()==c.getX() && j.getY()==c.getY()) matrix[j.getX()][j.getY()]='a';
    }
}
 
