import java.util.*;


public class Chemin {
    //ligne et colonne de la matrice
    private static final int ROW = 5;
    private static final int COL = 5;
    
    private static class Coordonnee
    {
        int x;
        int y;
        int dist;
        Coordonnee parent;
        
        //initialise une coordonnee
        public Coordonnee(int x, int y, int dist, Coordonnee parent)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.parent = parent;
        }
    }

    //return true si les coordonnees sont dans la matrice sinon false 
    private static boolean dansMatrice(int x, int y)
    {
        return (x >= 0 && x < ROW && y >= 0 && y < COL);
    }

    //permet d'interchanger les places une a une jusqu'a la place qu'on veut atteindre
    private static void changerPlace(Coordonnee[] chemin, int[][] matrice)
    {
        for (int i = 0; i < chemin.length - 1; i++)
        {
            Coordonnee current = chemin[i];
            Coordonnee next = chemin[i + 1];
            System.out.println("Déplacer les cases de (" + current.x + ", " + current.y + ") à (" + next.x + ", " + next.y + ")");
            int temp = matrice[current.x][current.y];
            matrice[current.x][current.y] = matrice[next.x][next.y];
            matrice[next.x][next.y] = temp;
        }
    }

    //Affiche si le chemin est trouvable ou non, si oui affiche le chemin a prendre et est mis dans un tableau de coordonnees
    private static void afficherChemin(Coordonnee destination, int[][] matrice)
    {
        if(destination == null)
        {
            System.out.println("Chemin introuvable !");
            return;
        }
        int tailleChemin = destination.dist;
        //chemin va contenir toutes les coordonnées du chemin a parcourir
        Coordonnee[] chemin = new Coordonnee[tailleChemin + 1];

        Coordonnee current = destination;
        for(int i = tailleChemin; i >= 0; i--)
        {
            chemin[i] = current;
            current = current.parent;

        }
        System.out.println("Chemin trouvé !");
        //affiche les coordonnées ou passer
        for (int i = 0; i <=tailleChemin; i++)
            System.out.println("(" + chemin[i].x + ", " + chemin[i].y + ")");

        changerPlace(chemin, matrice);
    }

    //fais une recherche en largeur de la matrice de la coordonnee source pour trouver la coordonnee destination 
    private static int[][] resolution(int[][] matrice, Coordonnee source, Coordonnee destination)
    {
        boolean[][] visite = new boolean[ROW][COL];//pour savoir si les cases ont deja été visité ou non par le programme

        int[] rowOffset = {-1, 0, 0, 1};//-1 pour aller a gauche les deux 0 pour ne pas bouger et 1 pour aller a droite
        int[] colOffset = {0, -1, 1, 0};//0 ne pas bouger -1 pour aller a la case d'en haut +1 aller case d'en bas 

        Queue<Coordonnee> queue = new LinkedList<>();//la queue pour mettre chaque coordonne de dans
        queue.add(source);
        visite[source.x][source.y] = true;//case de source true car visité

        while(!queue.isEmpty())
        {
            //recupere et spprime la tete de la queue
            Coordonnee current = queue.poll();

            //regarde si la case ou on est est la meme que la destination
            if(current.x == destination.x && current.y == destination.y)
            {
                afficherChemin(current, matrice);
                return matrice;
            }

            //recherche toute les cases aux alentours de la case ou l'on est
            for (int i = 0; i < 4; i++) {
                int newx = current.x + rowOffset[i];
                int newy = current.y + colOffset[i];

                if(dansMatrice(newx, newy) && matrice[newx][newy] == 1 && !visite[newx][newy])
                {
                    visite[newx][newy] = true;
                    Coordonnee aCote = new Coordonnee(newx, newy, current.dist + 1, current);
                    queue.add(aCote);
                }
            }
                
        }
        afficherChemin(null, matrice);;
        return matrice;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 1, 0, 1},
            {1, 0, 1, 12, 1},
            {1, 1, 1, 0, 1}
        };
        
        Coordonnee source = new Coordonnee(3, 3, 0, null);
        Coordonnee destination = new Coordonnee(0, 0, 0, null);
        
        int[][] matrice =  resolution(matrix, source, destination);

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[0].length; j++)
              System.out.print(matrice[i][j] + "\t");
            System.out.println();
        }
    }
}
