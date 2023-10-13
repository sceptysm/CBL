package actors;

import java.util.Vector;

import items.Item;


/**
 *  Treasure class based on Actor class.
 *  Represented by a chest which nets the player an item.
 * 
 *  
 */
public class Treasure extends Actor {

    /**
     * Constructor for the Treasure child class.
     */
    public Treasure() {

        /* Acts as a healthless sprite right from the get-go.
        *  Hence moving into this actor results in interacting with the Treasure actor
        *  instead of having the need to "kill" it first.
        */
        healthPoints = 0;
        coins = random.nextInt(0, 50);

        //Add item to treasure's inventory at its construction.
        
        inventory = new Vector<Item>();
        inventory.add(null);

    }

    /**
     * Method called upon player interaction. Returns the 
     * item inside of the Treasure object (essentially inside of a chest).  
     * 
     * @return an item inside the treasure.
     */
    Item give() {

        //TODO: Implement change sprite to an opened treasure box. 

        return inventory.remove(0);
    }

    /**
     * Method called upon player interaction. Returns the coins
     * possessed by the Treasure. 
     * 
     * @return amount of coins.
     */
    int giveCoins() {
        return coins;
    }
}
