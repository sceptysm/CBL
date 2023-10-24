package actors;

import environment.Room;

/**
 * Door class extending Actor, when interacted with transports the player to the next room.
 */
public class Door extends Actor {

    Room nextRoom;
    String direction;

    /**
     * Constructor where the next room and orientation are configured.
     */
    public Door(int posX, int posY, Room r, String s) {
        super("door", posX, posY);

        nextRoom = r;
        direction = s;
    }
}
