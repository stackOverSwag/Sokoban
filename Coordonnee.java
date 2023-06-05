public class Coordonnee {
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
