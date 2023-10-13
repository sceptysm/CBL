package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Implement Key Handler

public class KeyboardHandler implements KeyListener {

    //keyboard Record
    public static boolean[] keyboardRecord = new boolean[100];

    public KeyboardHandler() {
        
    }
 
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        keyboardRecord[keyCode] = true;
        System.out.println((char) keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyboardRecord[keyCode] = false;
        System.out.println((char) keyCode);
    }

}