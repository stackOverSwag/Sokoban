import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Test{
	public static void main (String[] args) {
	Matrice matrice = new Matrice("1by1_clone_flower.txt");
	Joueur p = matrice.getJoueurs().get(0);
	System.out.println(matrice.toString());

	}
}
