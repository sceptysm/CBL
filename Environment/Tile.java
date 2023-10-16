package environment;

import actors.Actor;


public class Tile {
    
    public Actor occupant; //Actor at tile.

    Tile() {
        occupant = null;
    }

    /**
     * Method that paints a single Tile. 
     * 
     * @param i Index obtained through paintRoom() method.
     * @param j Index obtained through paintRoom() method.
     */
    void paintTile(int i, int j) {
        // For the diameter of the room paint the tile beginning at x=i and y=j.

        // i + size ; j + size

        if (occupant != null) {
            //paint occupant
        }
    }

    // Get methods for tile fields.

}
