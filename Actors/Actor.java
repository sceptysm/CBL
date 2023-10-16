package actors;

import environment.Room;
import java.util.Random;
import java.util.Vector;

import items.Item;

/**
 *  Parent object for PC and NPCs.
 */
public class Actor {

    //fields used for game generation.
    int positionX; //Index in the Tile Array
    int positionY; //Index in the Tile Array

    int tileSize = 8;
    int renderPositionX = positionX * tileSize;
    int renderPositionY = positionY * tileSize;

    Random random = new Random();
    public Room currentRoom;

    //in game stats
    public int healthPoints;
    public int strength;
    public int defense;
    public int agility;
    public int coins;


    //in game inventory of items
    public Vector<Item> inventory;


    //initialize a representation for the actor (such as a sprite).

    // Movement methods for each Actor. 
    public void moveUp() {

        //Checks what is in front of the actor
        boolean onGrid = (positionY - 1) > -1;
        Actor inFrontOf;

        if (onGrid) { //if in front of is a tile in the grid
            inFrontOf = currentRoom.getInFrontOf(positionX, positionY - 1);
            if (inFrontOf == null) { // if tile in front of is not occupied 
                currentRoom.getTileSet()[positionX][positionY - 1].occupant = this;
                currentRoom.getTileSet()[positionX][positionY].occupant = null;
                positionY -= 1;

            }   
        }

    }

    public void moveDown() {

        //Checks what is in front of the actor

        //condition for Index out of bounds
        boolean onGrid = (positionY + 1) < currentRoom.getRoomSize(); 
        Actor inFrontOf;

        if (onGrid) { //if in front of is a tile in the grid
            inFrontOf = currentRoom.getInFrontOf(positionX, positionY + 1);
            if (inFrontOf == null) { // if tile in front of is not occupied 
                currentRoom.getTileSet()[positionX][positionY + 1].occupant = this;
                currentRoom.getTileSet()[positionX][positionY].occupant = null;
                positionY += 1;

            }   
        }

    }

    public void moveLeft() {

        //Checks what is in front of the actor
        boolean onGrid = (positionX - 1) > -1;
        Actor inFrontOf;

        if (onGrid) { //if in front of is a tile in the grid
            inFrontOf = currentRoom.getInFrontOf(positionX - 1, positionY);
            if (inFrontOf == null) { // if tile in front of is not occupied 
                currentRoom.getTileSet()[positionX - 1][positionY].occupant = this;
                currentRoom.getTileSet()[positionX][positionY].occupant = null;
                positionX -= 1;

            }   
        }

    }

    public void moveRight() {

        //Checks what is in front of the actor

        //condition for Index out of bounds
        boolean onGrid = (positionX + 1) < currentRoom.getRoomSize();
        Actor inFrontOf;

        if (onGrid) { //if in front of is a tile in the grid
            inFrontOf = currentRoom.getInFrontOf(positionX + 1, positionY);
            if (inFrontOf == null) { // if tile in front of is not occupied 
                currentRoom.getTileSet()[positionX + 1][positionY].occupant = this;
                currentRoom.getTileSet()[positionX][positionY].occupant = null;
                positionX += 1;

            }   
        }

    }

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

    //Render Positions

    public int getRenderPositionX() {
        return renderPositionX + 192;
    }

    public int getRenderPositionY() {
        return renderPositionY + 96;
    }
}
