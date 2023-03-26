import java.awt.*;
import javax.swing.*;

public class KeyHandler implements KeyListener {

public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {} // NOT USED EVER

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // returns number of key pressed
        
        // SETS VALUE FOR LATER
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        
        // DOESN'T WORK FOR NOW
        @Override
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        @Override
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        @Override
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        @Override
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        
        // TODO
        // fix this dumb override issue!
    }
}
