/*
 * return: ctrl z
 * mouvements: fleches directionnelles
 * objectif matrice:
 * nom du monde est une lettre suivi d'un chiffre taille
 * creer un carré vide de n lignes et n colonnes avec les cases:
 *      ' 'vide
 *      '#'mur
 *      'B'boite
 *      'A'personnage
 *      '@'cible
 *      'b'boite sur cible
 *      'a'personnage sur cible
 * 
 * 
 * Objectif mouvements:
 * modifier cordonnées joueur et boite (x,y)
 * aller sur une case designé efficacement si possible
 * sinon rien (car boite sur le chemin ou bloqué)
 * methodes:
 *      +estlibre(p,d) pour savoir si le joueur p 
 *      peut se deplacer dans la direction d sanq bouger de caisse
 *      +suivant(p,d) modifie position de p si estlibre vrai
 *
 * Objectif graphisme:
 * importer bib swing et utiliser les methodes pour avoir
 * un affichage qui n'est pas sur le terminal mais sur une une 
 * interface graphique
 *      
 */

 import "mouvements.java";
 import "matrice.java";