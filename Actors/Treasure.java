package actors;

import java.util.Random;
import java.util.Vector;

import items.armor.*;
import items.consumables.*;
import items.helms.*;
import items.weapons.*;
import items.*;


/**
 *  Treasure class based on Actor class.
 *  Represented by a chest which nets the player an item.
 */
public class Treasure extends Actor {

    // Common Equipment
    public static final Equipment[] COMMON_EQUIPMENT = {
        new BasicSword(1),
        new BasicArmor(1),
        new BasicHelm(1)
    };

    // Rare equipment
    public static final Equipment[] RARE_EQUIPMENT = {
        new BasicSword(1),
        new BasicArmor(1),
        new BasicHelm(1)
    };
    
    // Legendary Equipment
    public static final Equipment[] LEGENDARY_EQUIPMENT = {
        new BasicSword(1),
        new BasicArmor(1),
        new BasicHelm(1)
    };

    // Consumables
    public static final Consumable[] CONSUMABLE_LIST = {
        new StrengthPotion(1),
        new DefensePotion(1),
        new AgilityPotion(1),
        new MaxHealthPotion(1),
        new QuickHealthPotion(1), 
        new QuickExperiencePotion(1)
    };

    private int stageNumber;

    /**
     * Constructor for the Treasure child class.
     */
    public Treasure(int posX, int posY, int stageNr) {

        /*
         * Acts as a healthless sprite right from the get-go.
         * Hence, moving into this actor results in interacting with the Treasure actor
         * instead of having the need to "kill" it first.
         */

        super("treasure", posX, posY);
        
        healthPoints = 0;
        coins = random.nextInt(0, 50);
        stageNumber = stageNr;
        inventory = new Vector<>();

        // Generate the Treasure's inventory
        generateInventory();
        System.out.println(inventory.toString());
        System.out.println(inventory.elementAt(0).getLevel());
    }

    /**
     * Generate the inventory of a Treasure actor.
     */
    void generateInventory() {
        // create a Random variable
        Random random = new Random();

        // Generate different rarities of items depending on stage number
        // TODO - BALANCING
        boolean genCommon = stageNumber < 6;
        boolean genRare = stageNumber > 4;
        boolean genLegendary = stageNumber > 8;
        boolean generatePotion = random.nextBoolean();

        if (genCommon) {
            int i = random.nextInt(COMMON_EQUIPMENT.length - 1);
            inventory.add(COMMON_EQUIPMENT[i]);
        }
        if (genRare) {
            int i = random.nextInt(RARE_EQUIPMENT.length - 1);
            inventory.add(RARE_EQUIPMENT[i]);
        }
        if (genLegendary) {
            int i = random.nextInt(LEGENDARY_EQUIPMENT.length - 1);
            inventory.add(LEGENDARY_EQUIPMENT[i]);
        }
        if (generatePotion) {
            int i = random.nextInt(CONSUMABLE_LIST.length - 1);
            inventory.add(CONSUMABLE_LIST[i]);
        }

        updateInventoryStats();
    }

    /**
     * Iterate through the inventory of the Treasure, updating the stats of each item.
     */
    void updateInventoryStats() {
        for (Item currentItem : inventory) {
            currentItem.updateStats(stageNumber);
        }
    }

    /**
     * Method called upon player interaction. Returns the 
     * item inside of the Treasure object (essentially inside of a chest).  
     * 
     * @return an item inside the treasure.
     */
    Item give() {
        //Implement change sprite to an opened treasure box.
        if (!inventory.isEmpty()) { 
            return inventory.remove(0);
        } else {
            return null;
        }
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
