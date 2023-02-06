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

public class matrice {
    private int n;
    public matrice(int n){
        int y,x;
        for(y = 0;y <n;y++){
            for(x=0;x<n;x++){  
                if(x==0 || x==n-1 ||y==0 ||y==n-1){
                    System.out.printf("#");
                }	
                else{
                    System.out.printf(" ");
                } 
            }
            System.out.printf("\n"); 
        }
    }
    public matrice(int n, pos p, pos b, pos cp, pos cb){
        super();
        


    }
}

