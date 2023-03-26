/*
 *  * objectif matrice:
 * nom du monde est une lettre suivi d'un chiffre taille
 * creer un carré vide de n lignes et n colonnes avec les cases:
 *      ' 'vide
 *      '#'mur
 *      'B'boite
 *      'A'personnage
 *      '@'cible
 *      'b'boite sur cible
 *      'a'personnage sur cible
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Matrice {
    File file = new File("1by1_clone_flower.txt");
     // gotta close the file idk how
 // not used yet Rayan, vscode is mad at me // it will be used later i hope
    private char[][] matrix;
    private int rows=0;
    private int cols=0;
    private String monde;
    //scanner.nextLine(); // Move the scanner to the next line

    // Create a matrix with the same properties as the file
    public Matrice(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.matrix = new char[rows][cols];
    }
    public Matrice(String file) throws FileNotFoundException{
        try (Scanner scanner = new Scanner(file)) {
            monde = scanner.next();
            String line;
            this.rows = scanner.nextInt();
            this.cols = this.rows;
            this.matrix = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                line = scanner.nextLine();
                for (int j = 0; j < cols; j++) {  
                    matrix[i][j] = line.charAt(j);
                    if(matrix[i][j]=='B') new Boite(16,16,i,j);
                    if(matrix[i][j]=='A') new Joueur(16,16,i,j);
                    if(matrix[i][j]=='@') new Cible(16,16,i,j);
                    if(matrix[i][j]=='@') new Mur(16,16,i,j);
                }
            }
            scanner.close();
        }
    }
    public Matrice(Matrice other) {
        rows = other.getRows();
        cols = other.getCols();
        matrix = new char[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = other.getElement(i, j);
            }
        }
    }

    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public char getElement(int i, int j) {
        return matrix[i][j];
    }
    
    public void setElement(int i, int j, char val) {
        matrix[i][j] = val;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("nom du monde : ").append(monde).append("\n taille du monde : ").append(rows).append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
        
    }
    
    
    /*
    
    
    public void suivant(Joueur j, Direction s) {
        if(estlibre(j,s)){
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
    public boolean estlibre(Joueur j, Direction s) {
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
    */
}
