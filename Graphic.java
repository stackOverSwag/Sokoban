import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class graphic extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton button;

    public MaFenetre() {
        setTitle("Ma fenetre");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        button = new JButton("Cliquez-moi");
        button.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(button);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String texte = textField.getText();
            JOptionPane.showMessageDialog(this, "Vous avez saisi : " + texte);
        }
    }

    public static void main(String[] args) {
        new MaFenetre();
    }
}
