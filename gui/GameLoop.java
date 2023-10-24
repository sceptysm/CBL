package gui;

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
    static Player player;
    private static Stage stage;
    public static Room currentRoom;

    //Variables inside the Stage

    static int stageNumber; 
    private static boolean playerDied = false;
    private static boolean inventoryIsOpen = false;
    

    GameLoop() {
        //Constructor not needed so far.
    }

    public static void startGame() {
        init();
    }

    public static void gameOver() {
        
    }

    public static void newStage() {
        stageNumber += 1;
        stage = new Stage(stageNumber, player);
        currentRoom = stage.startRoom;
        stage.generateStage(currentRoom);
    }

    private static void init() {
        stage = new Stage();
        stageNumber = 1;
        currentRoom = stage.startRoom;
        stage.generateStage(currentRoom);
        player = stage.player;
    }

    /**
     *  Updates the game loop.
     * @throws InterruptedException
     */
    public static void update() throws InterruptedException {

        Thread.sleep(5);

        if (playerDied) {
            painter.paintGameOver();
            return;
        }

        if (inventoryIsOpen) {
            painter.paintInventory();
        }

        if (KeyboardHandler.isKeyDown(KeyEvent.VK_W)) {
            player.moveUp();
            currentRoom.doMonstersTurn(player);
        } 
        if (KeyboardHandler.isKeyDown(KeyEvent.VK_A)) {
            player.moveLeft();
            currentRoom.doMonstersTurn(player);
        }  
        if (KeyboardHandler.isKeyDown(KeyEvent.VK_S)) {
            player.moveDown();
            currentRoom.doMonstersTurn(player);
        }  
        if (KeyboardHandler.isKeyDown(KeyEvent.VK_D)) {
            player.moveRight();
            currentRoom.doMonstersTurn(player);
        }

        if (KeyboardHandler.isKeyDown(KeyEvent.VK_I)) {
            inventoryIsOpen = !inventoryIsOpen;
        }
        //If the turn is over and the player has 0 hp:

        if (player.getHealthPoints() <= 0) {
            playerDied = true;
        } 

    }

    public static int getStageNumber() {
        return stageNumber;
    }

    public static void increaseStageNumber() {
        stageNumber += 1;
    }
}
