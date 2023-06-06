
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
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Thread;

public class Matrice {
    private char[][] matrice;
    private int rows;
    private int cols;
    private char monde = ' ';
    String file;
    private List<Joueur> joueurs;
    private List<Boite> boites;
    private List<Cible> cibles;
    private List<Mur> murs;

    // Create a matrice with the same properties as the file
    public Matrice(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrice = new char[rows][cols];
        this.joueurs = new ArrayList<>();
        this.boites = new ArrayList<>();
        this.cibles = new ArrayList<>();
        this.murs = new ArrayList<>();
    }

    public Matrice(String file) {
        try (Scanner scanner = new Scanner(new File(file))) {
            monde = scanner.next().charAt(0);
            rows = scanner.nextInt();
            cols = rows;
            String line = scanner.nextLine();
            this.joueurs = new ArrayList<>();
            this.boites = new ArrayList<>();
            this.cibles = new ArrayList<>();
            this.murs = new ArrayList<>();
            this.matrice = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                line = scanner.nextLine();
                for (int j = 0; j < cols; j++) {
                    matrice[i][j] = line.charAt(j);
                    if (Character.isUpperCase(matrice[i][j]) && matrice[i][j] != 'A')
                        boites.add(new Boite(32, 32, i, j));
                    else if (matrice[i][j] == 'A')
                        joueurs.add(new Joueur(32, 32, i, j));
                    else if (matrice[i][j] == '@')
                        cibles.add(new Cible(32, 32, i, j));
                    else if (matrice[i][j] == '#')
                        murs.add(new Mur(32, 32, i, j));
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public Matrice(Matrice other) {
        rows = other.getRows();
        cols = other.getCols();
        matrice = new char[rows][cols];
        joueurs = new ArrayList<>(other.getJoueurs());
        boites = new ArrayList<>(other.getBoites());
        cibles = new ArrayList<>(other.getCibles());
        murs = new ArrayList<>(other.getMurs());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrice[i][j] = other.getElement(i, j);
            }
        }
    }

    public char[][] getmatrice() {
        return matrice;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char getElement(int i, int j) {
        return matrice[i][j];
    }

    public void setElement(int i, int j, char val) {
        matrice[i][j] = val;
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

    public void movePlayer(int row, int col) {
        List<Joueur> joueurs = getJoueurs();
        Joueur joueur = joueurs.get(0);

        if (estLibre(joueur, row, col)) {
            setElement(joueur.getX(), joueur.getY(), ' '); // Effacer l'ancienne positiondu joueur
            joueur.setX(row);
            joueur.setY(col);
            setElement(row, col, 'A'); // Définir la nouvelle position du joueur
        }
    }

    public boolean estLibre(Joueur joueur, int row, int col) {
        char[][] matrice = getmatrice();
        List<Boite> boites = getBoites();

        // Vérifier si la case est libre et si aucune boîte ne bloque le chemin
        if (matrice[row][col] == ' ' && !isBlockedByBox(boites, row, col)) {
            // Vérifier si le joueur se déplace horizontalement ou verticalement
            if (row == joueur.getX()) {
                int start = Math.min(col, joueur.getY());
                int end = Math.max(col, joueur.getY());
                for (int c = start; c <= end; c++) {
                    if (matrice[row][c] == 'B' && !isBoxMoved(boites, row, c, joueur.getX(), end)) {
                        return false;
                    }
                }
            } else if (col == joueur.getY()) {
                int start = Math.min(row, joueur.getX());
                int end = Math.max(row, joueur.getX());
                for (int r = start; r <= end; r++) {
                    if (matrice[r][col] == 'B' && !isBoxMoved(boites, r, col, end, joueur.getY())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean isBlockedByBox(List<Boite> boites, int row, int col) {
        for (Boite boite : boites) {
            if (boite.getX() == row && boite.getY() == col) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxMoved(List<Boite> boites, int row, int col, int newRow, int newCol) {
        for (Boite boite : boites) {
            if (boite.getX() == row && boite.getY() == col) {
                if (boite.getX() == newRow && boite.getY() == newCol) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("nom du monde : ").append(monde).append("\ntaille du monde : ").append(rows).append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrice[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    public static boolean dansMatrice(char[][] matrice, int x, int y) {
        int col = matrice.length;
        int ligne = matrice[0].length;
        return (x >= 1 && x < ligne - 1 && y >= 1 && y < col - 1);
    }

    // permet d'interchanger les places une a une jusqu'a la place qu'on veut
    // atteindre
    public void changerPlace(Coordonnee[] chemin, char[][] matrice) {
        for (int i = 0; i < chemin.length - 1; i++) {
            Coordonnee current = chemin[i];
            Coordonnee next = chemin[i + 1];
            System.out.println(
                    "Déplacer les cases de (" + current.x + ", " + current.y + ") à (" + next.x + ", " + next.y + ")");
            movePlayer(next.x, next.y);
            try {
                Thread.sleep(1000); // Attendre pendant 1 seconde (1000 millisecondes)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Affiche si le chemin est trouvable ou non, si oui affiche le chemin a prendre
    // et est mis dans un tableau de coordonnees
    public void afficherChemin(Coordonnee destination, char[][] matrice) {
        if (destination == null) {
            System.out.println("Chemin introuvable !");
            return;
        }
        int tailleChemin = destination.dist;
        // chemin va contenir toutes les coordonnées du chemin a parcourir
        Coordonnee[] chemin = new Coordonnee[tailleChemin + 1];

        Coordonnee current = destination;
        for (int i = tailleChemin; i >= 0; i--) {
            chemin[i] = current;
            current = current.parent;

        }
        System.out.println("Chemin trouvé !");
        changerPlace(chemin, matrice);
    }

    // fais une recherche en largeur de la matrice de la coordonnee source pour
    // trouver la coordonnee destination
    public void resolution(char[][] matrice, Coordonnee source, Coordonnee destination) {
        int col = matrice.length;
        int ligne = matrice[0].length;
        boolean[][] visite = new boolean[ligne][col];// pour savoir si les cases ont deja été visité ou non par le
                                                     // programme

        int[] rowOffset = { -1, 0, 0, 1 };// -1 pour aller a gauche les deux 0 pour ne pas bouger et 1 pour aller a
                                          // droite
        int[] colOffset = { 0, -1, 1, 0 };// 0 ne pas bouger -1 pour aller a la case d'en haut +1 aller case d'en bas

        Queue<Coordonnee> queue = new LinkedList<>();// la queue pour mettre chaque coordonne de dans
        queue.add(source);
        visite[source.x][source.y] = true;// case de source true car visité

        while (!queue.isEmpty()) {
            // recupere et spprime la tete de la queue
            Coordonnee current = queue.poll();

            // regarde si la case ou on est est la meme que la destination
            if (current.x == destination.x && current.y == destination.y) {
                afficherChemin(current, matrice);
                return;
            }

            // recherche toute les cases aux alentours de la case ou l'on est
            for (int i = 0; i < 4; i++) {
                int newx = current.x + rowOffset[i];
                int newy = current.y + colOffset[i];

                if (dansMatrice(matrice, newx, newy) && matrice[newx][newy] == ' ' && !visite[newx][newy]) {
                    visite[newx][newy] = true;
                    Coordonnee aCote = new Coordonnee(newx, newy, current.dist + 1, current);
                    queue.add(aCote);
                }
            }

        }
        afficherChemin(null, matrice);
    }
}