package gui;

import actors.*;
import environment.Stage;
import java.awt.event.KeyEvent;


/**
 *  Class that handles game logic. Interprets user input,
 *  and reacts accordingly. 
 */
public class GameLoop {

    MouseHandler mouseHandler;

    //Control variables
    public static Player player;
    public static Actor testMonster;
    private static Stage stage;

    //Variables inside the Stage

    private static Monster[] monsters;
    private static int currentStage; 
    

    GameLoop() {
        //Constructor not needed so far.
    }

    public static void startGame() {
        init();
    }

    private static void init() {
        stage = new Stage();
        player = stage.player;
        testMonster = stage.testMonster;
        currentStage = 1;
        
    }

    /**
     *  Updates the game loop.
     */
    public static void update() {

        if (KeyboardHandler.isKeyDown(KeyEvent.VK_W)) {
            player.moveUp();

        } 
        if (KeyboardHandler.isKeyDown(KeyEvent.VK_A)) {
            player.moveLeft();
        }  
        if (KeyboardHandler.isKeyDown(KeyEvent.VK_S)) {
            player.moveDown();
        }  
        if (KeyboardHandler.isKeyDown(KeyEvent.VK_D)) {
            player.moveRight();
        }
        
    }
}
