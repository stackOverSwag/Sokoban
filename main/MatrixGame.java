import java.util.Scanner;

public class MatrixGame {

    // taille de la matrice
    private static final int ROWS = 10;
    private static final int COLS = 10;

    // symbole du personnage
    private static final char CHARACTER_SYMBOL = 'C';

    // symbole de la fin
    private static final char END_SYMBOL = 'D';

    // positions initiales du personnage et de la fin
    private static int characterRow = 0;
    private static int characterCol = 0;
    private static int endRow = 5;
    private static int endCol = 5;

    // matrice
    private static char[][] matrix = new char[ROWS][COLS];

    public static void main(String[] args) {

        // initialiser la matrice avec des tirets
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = '-';
            }
        }

        // placer le personnage sur la matrice
        matrix[characterRow][characterCol] = CHARACTER_SYMBOL;

        // placer la fin sur la matrice
        matrix[endRow][endCol] = END_SYMBOL;

        // afficher la matrice
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // déplacer le personnage
        Scanner scanner = new Scanner(System.in);
        while (characterRow != endRow || characterCol != endCol) {
            System.out.print("Direction (haut, bas, gauche, droite) : ");
            String direction = scanner.nextLine();

            int newCharacterRow = characterRow;
            int newCharacterCol = characterCol;

            switch (direction) {
                case "haut":
                    newCharacterRow--;
                    break;
                case "bas":
                    newCharacterRow++;
                    break;
                case "gauche":
                    newCharacterCol--;
                    break;
                case "droite":
                    newCharacterCol++;
                    break;
                case "fin":
                    int[] path = findPath(characterRow, characterCol, endRow, endCol);
                    if (path != null) {
                        for (int i = 0; i < path.length - 1; i += 2) {
                            int row = path[i];
                            int col = path[i + 1];
                            matrix[row][col] = '*';
                        }
                        matrix[endRow][endCol] = END_SYMBOL;
                    } else {
                        System.out.println("Il n'y a pas de chemin jusqu'à la fin.");
                    }
                    break;
                default:
                    System.out.println("Direction invalide.");
                    continue;
            }

            // vérifier si la nouvelle position est valide
            if (newCharacterRow < 0 || newCharacterRow >= ROWS || newCharacterCol < 0 || newCharacterCol >= COLS) {
                System.out.println("Position invalide.");
                continue;
            }

            // déplacer le personnage
            matrix[characterRow][characterCol] = '-';
            characterRow = newCharacterRow;
            characterCol = newCharacterCol;
            matrix[characterRow][characterCol] = CHARACTER_SYMBOL;

            // afficher la matrice
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0 ; j < COLS; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        System.out.println("Félicitations, vous avez atteint la fin !");
        scanner.close();
    }

    private static int[][] getNeighbours(int row, int col) {
        int[][] neighbours = new int[4][2];

        neighbours[0][0] = row - 1;
        neighbours[0][1] = col;
        neighbours[1][0] = row + 1;
        neighbours[1][1] = col;
        neighbours[2][0] = row;
        neighbours[2][1] = col - 1;
        neighbours[3][0] = row;
        neighbours[3][1] = col + 1;

        return neighbours;
    }

    private static int[] findPath(int startRow, int startCol, int endRow, int endCol) {
        boolean[][] visited = new boolean[ROWS][COLS];
        return findPathRecursive(startRow, startCol, endRow, endCol, visited);
    }

    private static int[] findPathRecursive(int row, int col, int endRow, int endCol, boolean[][] visited) {
        visited[row][col] = true;

        if (row == endRow && col == endCol) {
            int[] path = new int[2];
            path[0] = row;
            path[1] = col;
            return path;
        }

        int[][] neighbours = getNeighbours(row, col);

        for (int[] neighbour : neighbours) {
            int neighbourRow = neighbour[0];
            int neighbourCol = neighbour[1];

            if (neighbourRow < 0 || neighbourRow >= ROWS || neighbourCol < 0 || neighbourCol >= COLS) {
                continue;
            }

            if (visited[neighbourRow][neighbourCol]) {
                continue;
            }

            if (matrix[neighbourRow][neighbourCol] == '-') {
                continue;
            }

            int[] path = findPathRecursive(neighbourRow, neighbourCol, endRow, endCol, visited);
            if (path != null) {
                int[] fullPath = new int[path.length + 2];
                fullPath[0] = row;
                fullPath[1] = col;
                System.arraycopy(path, 0, fullPath, 2, path.length);
                return fullPath;
            }
        }

        return null;
    }
}

