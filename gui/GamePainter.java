package gui;

import actors.Actor;

import java.awt.Color;
import java.awt.Graphics;
import environment.Tile;
import environment.Room;
import environment.Wall;

public class GamePainter {

    private Graphics g;
    private Room currentRoom;
    private int roomSize;
    private int tileSize;
    private Tile[][] currentTileSet;

    /**
     * A constructor for the GamePainter class.
     * 
     * @param r the currently displayed room.
     * @param s the screenWidth 

     */
    GamePainter(Room r, int s) {
        // Initialize the variables to render
        tileSize = s;
        currentRoom = r;
        roomSize = Room.getRoomSize();

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
            g.fillRect(tile.getRenderPositionX(), tile.getRenderPositionY(),tileSize, tileSize);
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

    void paintUI() {

    }


    // Utility Get and Set Methods :

    public void setCurrentRoom(Room r) {
        currentRoom = r;

    }

    public void setGraphics(Graphics graphics) {
        this.g = graphics;
    }

    
}
