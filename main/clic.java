import javax.swing.*;
import java.util.List;
import java.io.File;
import java.awt.*;
import java.awt.event.*;

public class clic {

    public static void main(String[] args) {
        int i=0, cpt=0;  
        String directoryPath = "niveaux/";
        // Create a File object for the directory
        File directory = new File(directoryPath);
        // Get all files in the directory
        File[] files = directory.listFiles();
        // Check if the directory exists
        String[] fileNames={};
        if (directory.exists()) {
            // Check if it's a directory
            if (directory.isDirectory()) {
                // Check if any files exist in the directory
                if (files != null && files.length > 0) {
                    // Print the filenames and store them in an array
                    fileNames = new String[files.length];
                    for (cpt = 0; cpt < files.length; cpt++) {
                        File file = files[cpt];
                        String fileName = file.getName();
                        if (fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("txt")) {
                            fileNames[cpt] = fileName;
                        }
                    }
                    // Use the fileNames array as needed
                } else {
                    System.out.println("No files found in the directory.");
                }
            } else {
                System.out.println(directoryPath + " is not a directory.");
            }
        } else {
            System.out.println(directoryPath + " does not exist.");
        }
        cpt=0;

        Matrice matrix = new Matrice("niveaux/" + fileNames[cpt]);
        char[][] matrice = matrix.getmatrice();

        AffichageMatrice affichageMatrice = new AffichageMatrice(matrice);
        affichageMatrice.afficher();

        CliquerMatrice cliquerMatrice = new CliquerMatrice(matrice, affichageMatrice);
        cliquerMatrice.cliquer();
    }
}

class AffichageMatrice {

    private char[][] matrice;

    public AffichageMatrice(char[][] matrice) {
        this.matrice = matrice;
    }

    public void afficher() {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class CliquerMatrice {

    private char[][] matrice;
    private AffichageMatrice affichageMatrice;

    public CliquerMatrice(char[][] matrice, AffichageMatrice affichageMatrice) {
        this.matrice = matrice;
        this.affichageMatrice = affichageMatrice;
    }

    public void cliquer() {
        Frame frame = new Frame("Cliquer sur une case");
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(matrix.getRows(), matrice[0].length));

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                Label label = new Label(Integer.toString(matrice[i][j]));
                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        int x = -1;
                        int y = -1;

                        Component component = e.getComponent();
                        if (component instanceof Label) {
                            Label clickedLabel = (Label) component;
                            String text = clickedLabel.getText();
                            int value = Integer.parseInt(text);

                            // Recherche des coordonnées de la case cliquée
                            for (int i = 0; i < matrix.getRows(); i++) {
                                for (int j = 0; j < matrice[i].length; j++) {
                                    if (matrice[i][j] == value) {
                                        x = i;
                                        y = j;
                                        break;
                                    }
                                }
                            }

                            System.out.println("Coordonnées de la case cliquée : (" + x + ", " + y + ")");
                        }
                    }
                });

                frame.add(label);
            }
        }

        frame.setVisible(true);
    }
}
