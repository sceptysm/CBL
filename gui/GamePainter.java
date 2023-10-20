package gui;

import actors.Actor;
import actors.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import environment.Tile;
import environment.Room;
import environment.Wall;

public class GamePainter {

    private static Graphics g;
    private static Room currentRoom;
    private int roomSize;
    private static int tileSize;
    private Tile[][] currentTileSet;
    //private int screenWidth;
    //private int screenHeight;
    private Actor player;

    /**
     * A constructor for the GamePainter class.
     * 
     * @param r the currently displayed room.
     * @param s the screenWidth 

     */
    GamePainter(Room r) {
        // Initialize the variables to render
        tileSize = GamePanel.REAL_TILE_SIZE;
        currentRoom = r;
        roomSize = Room.getRoomSize();

        //screenHeight = GamePanel.SCREEN_HEIGHT;
        //screenWidth = GamePanel.SCREEN_WIDTH;

        System.out.println("Size: " + roomSize);
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

        // Paint the tile
        g.setColor(Color.BLACK);

        if (tile instanceof Wall) {
            g.setColor(Color.GRAY);
            g.fillRect(tile.getRenderPositionX(), tile.getRenderPositionY(), tileSize, tileSize);
        } 
        
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

        switch (actor.getType()) {
            case "player" -> {
                player = actor;
                g.setColor(Color.WHITE);
                g.fillRect(actor.getRenderPositionX(),
                        actor.getRenderPositionY(), tileSize, tileSize);
            }
            case "monster" -> {
                if (actor.healthPoints >= 0) {
                    g.setColor(Color.RED);
                    g.fillRect(actor.getRenderPositionX(), actor.getRenderPositionY(),
                            tileSize, tileSize);
                }
            }
            case "door" -> {
                if (actor.healthPoints >= 0) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(actor.getRenderPositionX(), actor.getRenderPositionY(),
                            tileSize, tileSize);
                }
            }
            case "obelisk" -> {
                if (actor.healthPoints >= 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(actor.getRenderPositionX(), actor.getRenderPositionY(),
                            tileSize, tileSize);
                }
            }
            case "treasure" -> {
                if (actor.healthPoints >= 0) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(actor.getRenderPositionX(), actor.getRenderPositionY(),
                            tileSize, tileSize);
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
        g.setFont(new Font("Dialog", Font.PLAIN, 25));


        // Draw Game Information
        g.drawString("STAGE: " + Integer.toString(GameLoop.getStageNumber()),
            tileSize * 2, tileSize * 1);
        g.drawString("SCORE: " + Integer.toString(((Player) player).getScore()),
            tileSize * 12, tileSize * 1);

        // Draw Player Stats
        g.drawString("HP: " + Integer.toString(player.healthPoints), tileSize * 2, tileSize * 16);
        g.drawString("COINS: ", tileSize * 13, tileSize * 16);
    }

    /**
     * A method that paints a message explaining a Player's interaction
     * with the game logic.
     * 
     * @param s
     */
    public static void paintMessage(String s) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Dialog", Font.PLAIN, 30));
        g.drawString(s, tileSize * 3, tileSize * 12);
        
    }


    // Utility Get and Set Methods :

    public static void setCurrentRoom(Room r) {
        currentRoom = r;
    }

    public void setGraphics(Graphics graphics) {
        g = graphics;
    }
    
}
