package environment;

/**
 * Wall class.
 */

public class Wall extends Tile {

    Wall(int posX, int posY) {
        super(posX, posY);
        walkable = false;
    }
}