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
     public static void main(String args[]) {  
        try {  
            //constructor of file class having file as argument  
            File file=new File("../niveaux/outlevels/1by1_clone_flower.txt");   
            FileInputStream fis=new FileInputStream(file);     //opens a connection to an actual file  
            System.out.println("file content: ");  
            int r=0;  
            while((r=fis.read())!=-1){  
                System.out.print((char)r);      //prints the content of the file  
            }  
        }  
        catch(Exception e){  
        e.printStackTrace();  
        }  
    } 
}

