package actors;

import java.util.Vector;
import items.Item;

/**
 *  Parent object for PC and NPCs.
 */
public class Actor {
    int positionX;
    int positionY;

    //in game stats
    int healthPoints;
    int strength;
    int defense;
    int agility;
    
    public int coins;

    //character type
    String type;


    //in game inventory of items
    public Vector<Item> inventory;


    //initialize a representation for the actor.

    // Movement methods for each Actor. 

    //Constructor for actor not yet used
    public Actor(String t) {

        type = t;
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
            currentRoom.tileSet[positionX][positionY - 1].occupant = this;
            positionY -= 1;
            updateRenderPosition();

        } else {
            System.out.println("in 4");
            interact(inFrontOf);
        }

    }
    void moveLeft() {
        positionX -= 1;
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
            currentRoom.tileSet[positionX - 1][positionY].occupant = this;
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
            currentRoom.tileSet[positionX + 1][positionY].occupant = this;
            positionX += 1;
            updateRenderPosition();

        } else {
            System.out.println("In3");
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
            System.out.println("Looting");
            coins = interactee.giveCoins();
            System.out.println("I now have: " + coins + " coins");
            interactee.healthPoints = -1; // test
            interactee.deleteFromCurrentTile();
            
        } else {
            System.out.println("Here");
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
     * Method that updates the position where an Actor is rendered on the JPanel.
     */
    void updateRenderPosition() {
        renderPositionX = positionX * tileSize;
        renderPositionY = positionY * tileSize;
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
        return renderPositionX + 192; //Non-variable integer is start position
    }

    public int getRenderPositionY() {
        return renderPositionY + 96;
    }

    public String getType() {
        return type;
    }
}
