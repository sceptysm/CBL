package actors;

import environment.Room;

public class Door extends Actor {

    Room nextRoom;
    String direction;

    public Door(int posX, int posY, Room r, String s) {
        super("door", posX, posY);

        nextRoom = r;
        direction = s;

    }


}
