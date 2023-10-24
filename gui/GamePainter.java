package gui;

import actors.Actor;
import actors.Player;
import environment.Room;
import environment.Tile;
import environment.Wall;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


public class GamePainter {  
    private static final int TILE_SIZE = GamePanel.REAL_TILE_SIZE;
    private static final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;

    private static Graphics g;
    private static Room currentRoom;

    private Tile[][] currentTileSet;
    private static Vector<String> messageQueue;
    private int roomSize;
    private static int messageTimer;
    private Actor player;
    private Point mousePosition;
    private static String currentMessage;


    /**
     * A constructor for the GamePainter class.
     * 
     * @param r the currently displayed room.
     * @param s the screenWidth 

     */
    GamePainter(Room r) {
        // Initialize the variables to render
        currentRoom = r;
        roomSize = Room.getRoomSize();
        messageQueue = new Vector<>();
        messageTimer = 0;

    }

    void paintGame() {
        paintRoom();
        paintUI();
    }
    
    /**
     * A method that paints the room in which the player is positioned.'
     * Relies on the currentRoom variable as the room which is now "displayed".
     */
    void paintRoom() {

        currentTileSet = currentRoom.getTileSet();

        for (int i = 0; i < roomSize; i++) {
            for (int j = 0; j < roomSize; j++) {
                paintTile(i, j);
            } 
        }

    }

    /**
     * Method that paints a single Tile. 
     * 
     * @param row Index obtained through paintRoom() method.
     * @param column Index obtained through paintRoom() method.
     */
    
    void paintTile(int row, int column) {
        Tile tile = currentTileSet[row][column];
        BufferedImage textureToPaint;


        // Paint the tile

        if (tile instanceof Wall) {
            textureToPaint = Textures.getSprite("wall");
        } else {
            textureToPaint = Textures.getSprite("floor");
        }

        g.drawImage(textureToPaint, tile.getRenderPositionX(),
            tile.getRenderPositionY(), TILE_SIZE, TILE_SIZE, null);
        
        if (tile.hasActor()) {
            // Paint the actor, by fetching the actor at the current tile.
            paintActor(tile.getActor());
        }

    }

    /**
     * A method that paints an actor occupying a tile based on their type.
     * 
     * @param actor the actor object to be painted.
     */
    void paintActor(Actor actor) {
        BufferedImage textureToPaint;

        
        switch (actor.getType()) {
            case "player" -> {
                player = actor;
                textureToPaint = Textures.getSprite(actor.getType());
                g.drawImage(textureToPaint, actor.getRenderPositionX(),
                    actor.getRenderPositionY(), TILE_SIZE, TILE_SIZE, null);
            }
            case "monster" -> {
                if (actor.healthPoints > 0) {
                    textureToPaint = Textures.getSprite(actor.getType());
                    g.drawImage(textureToPaint, actor.getRenderPositionX(),
                        actor.getRenderPositionY(), TILE_SIZE, TILE_SIZE, null);
                } else {
                    textureToPaint = Textures.getSprite("dead" + actor.getType());
                    g.drawImage(textureToPaint, actor.getRenderPositionX(),
                        actor.getRenderPositionY(), TILE_SIZE, TILE_SIZE, null);
                }
            }
            case "door" -> {
                textureToPaint = Textures.getSprite(actor.getType());
                g.drawImage(textureToPaint, actor.getRenderPositionX(), actor.getRenderPositionY(),
                    TILE_SIZE, TILE_SIZE, null);
            }
            case "obelisk" -> {
                textureToPaint = Textures.getSprite(actor.getType());
                g.drawImage(textureToPaint, actor.getRenderPositionX(), actor.getRenderPositionY(),
                    TILE_SIZE, TILE_SIZE, null);
            }
            case "treasure" -> {
                if (actor.inventoryIsEmpty()) {
                    textureToPaint = Textures.getSprite("empty" + actor.getType());
                    g.drawImage(textureToPaint, actor.getRenderPositionX(),
                        actor.getRenderPositionY(), TILE_SIZE, TILE_SIZE, null);
                } else {
                    textureToPaint = Textures.getSprite(actor.getType());
                    g.drawImage(textureToPaint, actor.getRenderPositionX(),
                        actor.getRenderPositionY(), TILE_SIZE, TILE_SIZE, null);
                }
            }
            default -> {
            }
        }      
    }

    /**
     * A method that paints the Game UI.
     */
    public void paintUI() {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD, 30));


        // Draw Game Information
        g.drawString("STAGE: " + Integer.toString(GameLoop.getStageNumber()),
            TILE_SIZE * 2, TILE_SIZE * 1);
        g.drawString("SCORE: " + Integer.toString(((Player) player).getScore()),
            TILE_SIZE * 12, TILE_SIZE * 1);

        // Draw Player Stats
        g.drawString("HP: " + Integer.toString(player.healthPoints), TILE_SIZE * 2, TILE_SIZE * 16);
        g.drawString("COINS: " + Integer.toString(player.coins), TILE_SIZE * 12, TILE_SIZE * 16);

        currentMessage = fetchCurrentMessage();
        if (currentMessage != null) {
            paintMessage();
        }
        
    }

    public void paintInventory() {
        // Paint the inventory rectangle
        int initialX = TILE_SIZE * 2;
        int initialY = TILE_SIZE * 2;
        int inventoryWidth = TILE_SIZE * 13;
        int inventoryHeight = TILE_SIZE * 13;


        g.setColor(Color.BLACK);
        g.fillRoundRect(initialX, initialY, inventoryWidth, inventoryHeight, 20, 20);

        g.setColor(Color.WHITE);
        g.drawRoundRect(initialX, initialY, inventoryWidth, inventoryHeight, 20, 20);

        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("Inventory", TILE_SIZE * 2, TILE_SIZE * 2 - 16);

        // Iterate through the inventory list and paint it.
        for (int i = 1; i <= player.inventory.size(); i++) {
            // Fetch the required sprite.
            BufferedImage itemToPaint = Textures.getSprite("sword");

            // Initialiaze variables for painting the inventory list.
            int initialItemX = TILE_SIZE * 12 + 16;
            int initialItemY = TILE_SIZE * 2 * i + 16;
            int itemBoxWidth = TILE_SIZE * 2;
            int itemBoxHeight = TILE_SIZE * 2 - 16;
            Rectangle rectangle = new Rectangle(initialItemX, initialItemY,
                itemBoxWidth, itemBoxHeight);

            //Paint the inventory list
            g.setColor(Color.BLACK);
            g.fillRoundRect(initialItemX, initialItemY, itemBoxWidth,
                itemBoxHeight, 20, 20);
            g.drawImage(itemToPaint, initialItemX, initialItemY, itemBoxWidth,
                itemBoxHeight, null);
            g.setColor(Color.WHITE);
            g.drawRoundRect(initialItemX, initialItemY, itemBoxWidth,
                itemBoxHeight, 20, 20);

            // Check if the mouse cursor is placed over the position of the current item box.
            if (rectangle.contains(mousePosition)) {
                // Draw a selection over a hovered inventory position.
                Graphics2D g = (Graphics2D) GamePainter.g;
                g.setStroke(new BasicStroke(2));
                g.setColor(Color.WHITE);
                g.drawRoundRect(initialItemX, initialItemY, itemBoxWidth,
                    itemBoxHeight, 20, 20);
                
                // Return the stroke to its default width.
                g.setStroke(new BasicStroke(1));
            }
            
        }

    }

    public void paintGameOver() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("You perished in the dream...", (TILE_SIZE * 5), TILE_SIZE * 6);
        g.setFont(new Font("Courier", Font.BOLD, 40));
        g.drawString("Game Over Bruh..", (TILE_SIZE * 5), TILE_SIZE * 7);

    }

    /**
     * A method that paints a message explaining a Player's interaction
     * with the game logic.
     * 
     * @param s
     */
    public static void paintMessage() {
        if (currentMessage != null) {
            messageTimer--; //Subtract a tick and paint the current message.

            // Draw the message box.
            Graphics2D g = (Graphics2D) GamePainter.g;
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.BLACK);
            g.fillRoundRect(TILE_SIZE * 2, TILE_SIZE * 12, TILE_SIZE * 13,
                TILE_SIZE * 2, 10, 10);

            g.setColor(Color.WHITE);
            g.drawRoundRect(TILE_SIZE * 2, TILE_SIZE * 12, TILE_SIZE * 13,
                TILE_SIZE * 2, 10, 10);
        
            // Draw the message.
            g.setStroke(new BasicStroke(1));
            g.setFont(new Font("Courier", Font.BOLD, 25));
            g.drawString(currentMessage, TILE_SIZE * 2 + 16, TILE_SIZE * 12 + 32);
        }

        
    }

    static String fetchCurrentMessage() {
        // If the queue is empty and there is no current message.
        // Return current message (null).
        if (messageQueue.isEmpty() && currentMessage == null) {
            return currentMessage;
        }

        // If the queue is empty and there is a current message.
        // Return current message.
        if (messageQueue.isEmpty() && currentMessage != null && messageTimer <= 0) {
            return null;
        } else if (messageQueue.isEmpty() && currentMessage != null) {
            return currentMessage;
        }

        // Else if a timer is elapsed, fetch new message.
        if (messageTimer <= 0) {
            messageTimer = 96; // Reset the timer.
            return messageQueue.remove(0);

        } 
        
        return currentMessage;
    }

    public static void addMessage(String s) {
        messageQueue.add(s);
    }

    // Utility Get and Set Methods :

    public static void setCurrentRoom(Room r) {
        currentRoom = r;
    }

    public void setMousePosition(Point mouse) {
        mousePosition = mouse;
    }

    public void setGraphics(Graphics graphics) {
        g = graphics;
    }
    
}
