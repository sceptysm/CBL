package actors;

import environment.Room;
import java.util.Random;
import java.util.Vector;
import items.Item;
import gui.GameLoop;
import gui.GamePainter;
import gui.GamePanel;

/**
 *  Parent object for PC and NPCs.
 */
public class Actor {

    //fields used for game generation.
    int positionX; //Index in the Tile Array
    int positionY; //Index in the Tile Array

    int tileSize = GamePanel.REAL_TILE_SIZE;
    int renderPositionX;
    int renderPositionY;

    Random random = new Random();
    public Room currentRoom;

    //in game stats
    public int healthPoints;
    public int strength;
    public int defense;
    public int agility;
    public int coins;

    //character type
    String type;


    //in game inventory of items
    public Vector<Item> inventory;


    //initialize a representation for the actor (such as a sprite).

    // Movement methods for each Actor. 

    //Constructor for actor not yet used
    /*
    public Actor(String t) {
        type = t;
        //updateRenderPosition();
    }
    */

    public Actor(String t, int posX, int posY) {
        type = t;
        positionX = posX;
        positionY = posY;
        healthPoints = 0;

        updateRenderPosition();
    }

    public void moveUp() {

        //Checks what is in front of the actor
        boolean onGrid = (positionY - 1) > -1;
        Actor inFrontOf;

        if (!onGrid) { //If in front of is not a tile in the grid :
            return;
        }
         
        // Else : 
        inFrontOf = currentRoom.getInFrontOf(positionX, positionY - 1);
        if (inFrontOf == null) { // if tile in front of is not occupied 
            deleteFromCurrentTile();
            currentRoom.tileSet[positionX][positionY - 1].setActor(this);
            positionY -= 1;
            updateRenderPosition();

        } else {
            System.out.println("in 4");
            interact(inFrontOf);

            /* 
            if (currentRoom.tileSet[inFrontOf.getPositionX()][inFrontOf.getPositionY()].getActor() == null) {
                currentRoom.tileSet[positionX][positionY - 1].setActor(this);
                positionY -= 1;
                updateRenderPosition();
            }
            */
        }

    }

    /**
     *  A method.
     */
    public void moveDown() {

        //Checks what is in front of the actor

        //condition for Index out of bounds
        boolean onGrid = (positionY + 1) < currentRoom.getRoomSize(); 
        Actor inFrontOf;

        if (!onGrid) { //If in front of is not a tile in the grid :
            return;
        }
         
        // Else : 
        inFrontOf = currentRoom.getInFrontOf(positionX, positionY + 1);
        if (inFrontOf == null) { // if tile in front of is not occupied 
            deleteFromCurrentTile();
            currentRoom.tileSet[positionX][positionY + 1].setActor(this);
            positionY += 1;
            updateRenderPosition();

        } else {

            interact(inFrontOf);

            /* 
            if (currentRoom.tileSet[inFrontOf.getPositionX()][inFrontOf.getPositionY()].getActor() == null) {
                currentRoom.tileSet[positionX][positionY + 1].setActor(this);
                positionY += 1;
                updateRenderPosition();
            }
            */
        }

    }

    public void moveLeft() {

        //Checks what is in front of the actor
        boolean onGrid = (positionX - 1) > -1;
        Actor inFrontOf;

        if (!onGrid) { //If in front of is not a tile in the grid :
            return;
        }
         
        // Else : 
        inFrontOf = currentRoom.getInFrontOf(positionX - 1, positionY);
        if (inFrontOf == null) { // if tile in front of is not occupied 
            deleteFromCurrentTile();
            currentRoom.tileSet[positionX - 1][positionY].setActor(this);;
            positionX -= 1;
            updateRenderPosition();

        } else {
            interact(inFrontOf);

            /*
            if (currentRoom.tileSet[inFrontOf.getPositionX()][inFrontOf.getPositionY()].getActor() == null) {
                currentRoom.tileSet[positionX - 1][positionY].setActor(this);;
                positionX -= 1;
                updateRenderPosition();
            }
            */
        } 

    }

    public void moveRight() {

        //Checks what is in front of the actor

        //condition for Index out of bounds
        boolean onGrid = (positionX + 1) < currentRoom.getRoomSize();
        Actor inFrontOf;

        if (!onGrid) { //If in front of is not a tile in the grid :
            return;
        }
         
        // Else : 
        inFrontOf = currentRoom.getInFrontOf(positionX + 1, positionY);
        if (inFrontOf == null) { // if tile in front of is not occupied 
            deleteFromCurrentTile();
            currentRoom.tileSet[positionX + 1][positionY].setActor(this);
            positionX += 1;
            updateRenderPosition();

        } else {
            System.out.println("In3");
            interact(inFrontOf);   
            
            /* 
            if (currentRoom.tileSet[inFrontOf.getPositionX()][inFrontOf.getPositionY()].getActor() == null) {
                currentRoom.tileSet[positionX + 1][positionY].setActor(this);
                positionX += 1;
                updateRenderPosition();
            }
            */
            
        }

    }


    /**
     * Method that initializes actor interaction from which further 
     * in-game interactions are called based on the interaction required 
     * in regard to the type/state of actor with which this actor interacts.
     * 
     * @param interactee the actor with which this actor interacts
     */
    void interact(Actor interactee) {

        System.out.println(interactee);
        if (interactee.healthPoints <= 0) { //If the HP
            //loot the dead body:
            ((Player) this).doSpecialInteraction(interactee);

            
        } else {
            System.out.println("Here");
            attack(interactee);
        }
    }

    

    void loot() {


    }

    /**
     * Method that is called when an actor attacks another actor.
     * 
     * @param d the defending actor
     */
    void attack(Actor d) {
        Actor defender = d;

        // Math determination of damage dealt to defender.
        System.out.println("Defender now has " + defender.healthPoints + " HP");
        defender.healthPoints -= strength;
        GamePainter.paintMessage("Dealt " + strength + " damage to " + defender.getType());
        System.out.println("Attacker attacks for: " + strength);
        System.out.println("Defender now has " + defender.healthPoints + " HP");
    }

    /**
     *  Return coins held by the actor.
     * 
     * @return amount of coins.
     */
    int giveCoins() {
        return coins;
    }


    // Utility Methods : 

    /**
     * Method that updates the position where an Actor is rendered on the JPanel.
     */
    void updateRenderPosition() {
        renderPositionX = (positionX) * tileSize;
        renderPositionY = (positionY) * tileSize;
    }

    void updatePositionInNewRoom(Actor d) {

        //Based on door location update player location

        Door door = (Door) d;

        switch (door.direction) {

            case "north" -> {
                positionX = 4;
                positionY = 7;
                System.out.println("north");
            }
            case "east" -> {
                positionX = 1;
                positionY = 4;
            }
            case "south" -> {
                positionX = 4;
                positionY = 1;
            }
            case "west" -> {
                positionX = 7;
                positionY = 4;
            }
            default -> {
            }
        }

        currentRoom.tileSet[positionX][positionY].setActor(this, currentRoom);
        updateRenderPosition();
        GamePainter.setCurrentRoom(currentRoom);

    }

    void updatePositionInNewStage() {
        

    }

    /**
     * Method that nulls the occupant at the index position of this actor
     * in the current room.
     */
    void deleteFromCurrentTile() {
        currentRoom.tileSet[positionX][positionY].occupant = null;

    }

    // Utility Get and Set Methods :

    public int getPositionX() {
        return positionX;
    }
    
    public int getPositionY() {
        return positionY;
    }

    public int getRenderPositionX() {
        return renderPositionX + 4 * tileSize; //Non-variable integer is start position
    }

    public int getRenderPositionY() {
        return renderPositionY + 2 * tileSize;
    }

    public String getType() {
        return type;
    }

    public void setCurrentRoom(Room r) {
        currentRoom = r;
    }

    public void setPositionX(int x) {
        positionX = x;
    }

    public void setPositionY(int y) {
        positionY = y;
    }
}
