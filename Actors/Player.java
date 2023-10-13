package Actors;

import Items.Item;
import java.util.Vector;

import Environment.Room;

public class Player extends Actor {


    public Player(int posX, int posY) {
        super(posX, posY);

        healthPoints = 20;
        strength = 1;
        defense = 1;
        agility = 1;
        inventory = new Vector<Item>();
    }

    // !!! would probably be best to implement this method to all actors that can move
    // then we can iterate through every actor in the room, do the movement, resolve everything
    // and THEN we draw the room at the very end to save computational shit
    void moveAndInteract(String type, Room currentRoom){
        if (type.equals("up")) {
            // check for isEmpty, walkability etc.
            if (currentRoom.tileSet[positionX][positionY - 1] == null){ // add more checks here
                currentRoom.tileSet[positionX][positionY] = null;
                moveUp();
                currentRoom.tileSet[positionX][positionY] = this;
            } 
            else if (currentRoom.tileSet != null) { // more checks
                // interact with some object
            }
            else {
                // do nothing e.g. walking into a wall
            }
        }
    }

    //method for attacking

    //method for taking items
}
