package Environment;

import Actors.Actor;


public class Tile {

    
    private static final int TILE_SIZE = 100; // Diameter of the Room.
    Actor occupant; //Actor at tile.

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
    public int getTileSize() {
        return TILE_SIZE;
    }

}
