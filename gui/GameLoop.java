package gui;

import actors.*;
import java.awt.event.KeyEvent;

/**
 *  Class that handles game logic. Interprets user input,
 *  and reacts accordingly. 
 */
public class GameLoop {

    KeyboardHandler keyHandler;
    MouseHandler mouseHandler;

    private Player player = new Player();

    GameLoop (KeyboardHandler k, MouseHandler m) {

        //Pass Arguments as Fields
        keyHandler = k;
        mouseHandler = m;

    }

    /**
     *  Updates the game loop.
     */
    void update() {
        if (keyHandler.keyboardRecord[KeyEvent.VK_W]) {
            player.moveUp();
        } 
        if (keyHandler.keyboardRecord[KeyEvent.VK_A]) {
            player.moveLeft();
        }  
        if (keyHandler.keyboardRecord[KeyEvent.VK_S]) {
            player.moveDown();
        }  
        if (keyHandler.keyboardRecord[KeyEvent.VK_D]) {
            player.moveRight();
        }
        try {
            Thread.sleep(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
