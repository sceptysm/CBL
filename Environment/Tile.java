package environment;

import actors.Actor;


public class Tile {
    
    private int positionX;
    private int positionY;
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

    public void setActor(Actor a) {
        occupant = a;
    }
}