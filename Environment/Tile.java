package environment;

import actors.Actor;


public class Tile {
    
    private int positionX;
    private int positionY;
    private int renderPositionX;
    private int renderPositionY;
    public boolean walkable;
    public Actor occupant;  //Actor at tile.
    

    Tile() {
        occupant = null;
        walkable = true;
    }

    /**
     * 
     */
    Tile(int posX, int posY) {
        positionX = posX;
        positionY = posY;
        walkable = true;
        updateRenderPosition();
    }

    void updateRenderPosition() {
        int tileSize = 48;
        renderPositionX = positionX * tileSize;
        renderPositionY = positionY * tileSize;
    }

    /**
     * A method that checks whether the called on Tile object 
     * has an Actor that occupies the tile. 
     * 
     * @return true if it has an occupying Actor object, false if not.
     */
    public boolean hasActor() {
        if (occupant != null) {
            return true;
        }

        return false;
    }

    // Get and Set methods for Tile fields.

    public Actor getActor() {
        return occupant;
    }

    public void setActor(Actor a, Room r) {
        occupant = a;
        occupant.setCurrentRoom(r);
    }

    public int getRenderPositionX() {
        return renderPositionX + 192;
    }

    public int getRenderPositionY() {
        return renderPositionY + 96;
    }
}