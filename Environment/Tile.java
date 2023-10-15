package Environment;

public class Tile {
    private static final int TILE_SIZE = 100; // Diameter of the Room.
    public int positionX, positionY;

    /**
     * Method that paints a single Tile. 
     * 
     * @param i Index obtained through paintRoom() method.
     * @param j Index obtained through paintRoom() method.
     */

    public Tile (int posX, int posY){
        this.positionX = posX;
        this.positionY = posY;
    }

    void paintTile(int i, int j) {
        // For the diameter of the room paint the tile beginning at x=i and y=j.

        // i + size ; j + size
    }

    // Get methods for tile fields.
    public int getTileSize() {
        return TILE_SIZE;
    }
}

class Wall extends Tile {
    boolean walkable;

    Wall (int posX, int posY) {
        super(posX, posY);
        walkable = false;
    }
}

class Door extends Tile {
    boolean walkable;
    // the room the door is pointing to
    Room pointRoom;

    Door (int posX, int posY) {
        super(posX, posY);
        walkable = true;
    }
}

