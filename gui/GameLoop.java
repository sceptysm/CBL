package gui;

import actors.*;
import environment.Stage;
import java.awt.event.KeyEvent;

/**
 *  Class that handles game logic. Interprets user input,
 *  and reacts accordingly. 
 */
public class GameLoop {

    KeyboardHandler keyHandler;
    MouseHandler mouseHandler;

    private Player player;
    Stage stage;


    GameLoop (KeyboardHandler k, MouseHandler m, Player p, Stage s) {

        //Pass Arguments as Fields
        keyHandler = k;
        mouseHandler = m;

        player = p;
        stage = s;

        player.currentRoom = stage.getStartRoom();
        stage.getStartRoom().getTileSet()[player.getPositionX()][player.getPositionY()].occupant = player;

    }

    /**
     *  Updates the game loop.
     */
    void update() {

        if (keyHandler.isKeyDown(KeyEvent.VK_W)) {
            player.moveUp();

        } 
        if (keyHandler.isKeyDown(KeyEvent.VK_A)) {
            player.moveLeft();
        }  
        if (keyHandler.isKeyDown(KeyEvent.VK_S)) {
            player.moveDown();
        }  
        if (keyHandler.isKeyDown(KeyEvent.VK_D)) {
            player.moveRight();
        }
    }
}
