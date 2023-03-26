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
    private char[][] matrix;
    private int rows=0;
    private int cols=0;
    private char monde;

    // Create a matrix with the same properties as the file
    public Matrice(int rows, int cols){
    this.rows = rows;
    this.cols = cols;
    this.matrix = new char[rows][cols];
    }

    public Matrice(String file) {
        try (Scanner scanner = new Scanner(new File(file))) {
            monde = scanner.next().charAt(0);
            String line;
            this.rows = scanner.nextInt();
            this.cols = this.rows;
            this.matrix = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                line = scanner.nextLine();
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.next().charAt(0);
                    if (matrix[i][j] == 'B')
                        new Boite(32, 32, i, j);
                    if (matrix[i][j] == 'A')
                        new Joueur(32, 32, i, j);
                    if (matrix[i][j] == '@')
                        new Cible(32, 32, i, j);
                    if (matrix[i][j] == '#')
                        new Mur(32, 32, i, j);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
     
 }
 