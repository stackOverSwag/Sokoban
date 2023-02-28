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


public class matrice {
    public static void main(String[] args) throws FileNotFoundException {
      File file = new File("../niveaux/outlevels/1by1_clone_flower.txt");
      Scanner scanner = new Scanner(file);
      String monde=scanner.next();
      // Get the number of rows and columns from the file
      
      int numRows = scanner.nextInt();
      int numCols = numRows;
      scanner.nextLine(); // Move the scanner to the next line

      // Create a matrix with the same properties as the file
      char[][] matrix = new char[numRows][numCols];

      // Fill the matrix with data from the file
      for (int i = 0; i < numRows; i++) {
          String line = scanner.nextLine();
          for (int j = 0; j < numCols; j++) {
              matrix[i][j] = line.charAt(j);
          }
      }

      // Print the matrix to the console
      for (int i = 0; i < numRows; i++) {
          for (int j = 0; j < numCols; j++) {
              System.out.print(matrix[i][j] + " ");
          }
          System.out.println();
      }
    }
}
