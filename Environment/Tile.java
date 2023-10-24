package environment;

import actors.Actor;
import gui.GamePanel;


public class Tile {
    
    private int positionX;
    private int positionY;
    private int renderPositionX;
    private int renderPositionY;
    protected boolean walkable;
    public Actor occupant;  //Actor at tile.

    int tileSize = GamePanel.REAL_TILE_SIZE;

    /**
     * 
     */
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

    /**
     * 
     */
    void updateRenderPosition() {
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

    public void setActor(Actor a) {
        occupant = a;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean b) {
        walkable = b;
    }

    public int getRenderPositionX() {
        return renderPositionX + 4 * tileSize;
    }

    public int getRenderPositionY() {
        return renderPositionY + 2 * tileSize;
    }
}