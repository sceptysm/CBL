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

    int tileSize = 48;
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


    //in game inventory of items
    public Vector<Item> inventory;


    //initialize a representation for the actor (such as a sprite).

    // Movement methods for each Actor. 

    
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
            currentRoom.getTileSet()[positionX][positionY - 1].occupant = this;
            positionY -= 1;
            updateRenderPosition();

        } else {
            interact(inFrontOf);
        }

    }

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
            currentRoom.getTileSet()[positionX][positionY + 1].occupant = this;
            positionY += 1;
            updateRenderPosition();

        } else {
            interact(inFrontOf);
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
            currentRoom.getTileSet()[positionX - 1][positionY].occupant = this;
            positionX -= 1;
            updateRenderPosition();

        } else {
            interact(inFrontOf);
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
            currentRoom.getTileSet()[positionX + 1][positionY].occupant = this;
            positionX += 1;
            updateRenderPosition();

        } else {
            interact(inFrontOf);
        }

    }


    /**
     * Method that initializes actor interaction from which further 
     * in-game interactions are called based on the interaction required 
     * in regards to the type/state of actor with which the this actor interacts.
     * 
     * @param i the actor with which this actor interacts
     */
    void interact(Actor i) {
        Actor interactee = i; 

        if (interactee.healthPoints <= 0) { //If the HP 
            //loot the dead body: 
            interactee.giveCoins();
            
        } else {
            attack(interactee);
        }
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
     *  Method that updates the position where an Actor is rendered on the JPanel.
     */
    void updateRenderPosition() {
        renderPositionX = positionX * tileSize;
        renderPositionY = positionY * tileSize;
    }

    void deleteFromCurrentTile() {
        currentRoom.getTileSet()[positionX][positionY].occupant = null;

    }

    public int getPositionX() {
        return positionX;
    }
    
    public int getPositionY() {
        return positionY;
    }

    public int getRenderPositionX() {
        return renderPositionX + 192;
    }

    public int getRenderPositionY() {
        return renderPositionY + 96;
    }
}
