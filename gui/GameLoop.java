package gui;

import actors.Actor;
import actors.Monster;
import actors.Player;
import environment.Room;
import environment.Stage;
import java.awt.event.KeyEvent;


/**
 *  Class that handles game logic. Interprets user input,
 *  and reacts accordingly. 
 */
public class GameLoop {

    MouseHandler mouseHandler;
    public static GamePainter painter;

    //Control variables
    public static Player player;
    public static Actor testMonster;
    private static Stage stage;
    public static Room currentRoom;

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
        currentStage = 1;
        currentRoom = stage.startRoom;
        stage.generateStage(currentRoom);
        player = stage.player;
        stage.printTest();
        System.out.println(currentRoom);

        
    }

    /**
     *  Updates the game loop.
     * @throws InterruptedException
     */
    public static void update() throws InterruptedException {

        Thread.sleep(12);

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
