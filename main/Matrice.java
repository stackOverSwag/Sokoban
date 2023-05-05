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
 import java.util.List;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 
 public class Matrice {
    private char[][] matrix;
    private int rows=0;
    private int cols=0;
    private char monde;
    String fileName ="1by1_clone_flower.txt";
    private List<Joueur> joueurs;
    private List<Boite> boites;
    private List<Cible> cibles;
    private List<Mur> murs;
    // Create a matrix with the same properties as the file
    public Matrice(int rows, int cols){
    this.rows = rows;
    this.cols = cols;
    this.matrix = new char[rows][cols];
    }

    public Matrice(String file) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            monde = scanner.next().charAt(0);
            rows = scanner.nextInt();
            cols = rows;
            String line = scanner.nextLine();
            this.matrix = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                line = scanner.nextLine();
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = line.charAt(j);
                    if (Character.isUpperCase(matrix[i][j]) && matrix[i][j] != 'A')
                        boites.add(new Boite(32, 32, i, j));
                    else if (matrix[i][j] == 'A')
                        joueurs.add(new Joueur(32, 32, i, j));
                    else if (matrix[i][j] == '@')
                        cibles.add(new Cible(32, 32, i, j));
                    else if (matrix[i][j] == '#')
                        murs.add(new Mur(32, 32, i, j)); 
                }
                
            } scanner.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
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

    public char[][] getMatrix() {
        return matrix;
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

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public List<Boite> getBoites() {
        return boites;
    }

    public List<Cible> getCibles() {
        return cibles;
    }

    public List<Mur> getMurs() {
        return murs;
    }
     
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("nom du monde : ").append(monde).append("\ntaille du monde : ").append(rows).append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
        
    }  
     
 }
 