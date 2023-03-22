import Matrice.java;


public class Boite extends Case implements Direction{
    private int x, y;
    public Boite(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void fin(Case b, Case c){
        if(b.getX()==c.getX() && b.getY()==c.getY()) matrix[b.getX()][b.getY()]="b";
    }
}
