package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that handles all keyboard input. It is utilized in the GamePanel class. 
 */
public class KeyboardHandler implements KeyListener {

    private static boolean[] keyboardRecord = new boolean[100]; //Holds whether a key is pressed
    private static int pressDelay = 0; //Input delay

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


    /**
     *  Method for checking whether a key is pressed down. 
     * 
     * @param key the character keyCode is passed to check whether its related key is down.
     * @return true when it is pressed down and without pressing delay, false when otherwise.
     */
    public static boolean isKeyDown(int key) {
        if (keyboardRecord[key] && pressDelay <= 0) {
            pressDelay = 240; // Press Delay Assures that key input isn't astronomically fast.
            
            /*
             * Can potentially be changed into a thread sleeper later on. (not urgent)
             */
            return true;
        } else {
            pressDelay--;
            return false;
        }
    }

}