import java.awt.*;
import javax.swing.*;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {} // NOT USED EVER

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // returns number of key pressed
        
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
  
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
       
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
    
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    public void getDirection() {

        if (upPressed && 
            !downPressed && 
            !leftPressed && 
            !rightPressed) {

            return Direction.NORD;
        } 

        else if (!upPressed && 
                 downPressed && 
                 !leftPressed && 
                 !rightPressed) {

            return Direction.SUD;
        } 

        else if (!upPressed && 
                 !downPressed && 
                 leftPressed && 
                 !rightPressed) {

            return Direction.OUEST;
        } 

        else if (!upPressed && 
                 !downPressed && 
                 !leftPressed && 
                 rightPressed) {

            return Direction.EST;
        }
    }
}
