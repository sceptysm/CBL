package environment;

import actors.Actor;


public class Tile {
    
    public Actor occupant; //Actor at tile.

    Tile() {
        occupant = null;
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

    // Get methods for Tile fields.

    public Actor getActor() {
        return occupant;
    }
    

}
