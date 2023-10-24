package environment;

/**
 * Wall class, special type of Tile that determines the bounds of a Room.
 */

public class Wall extends Tile {

    /**
     * Constructor.
     */
    Wall(int posX, int posY) {
        super(posX, posY);

        // Attribute preventing Actors from walking onto Walls
        walkable = false;
    }
}