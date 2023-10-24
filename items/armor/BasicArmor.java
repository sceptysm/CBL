package items.armor;

/**
 * BasicArmor class.
 */

public class BasicArmor extends Armor {

    /**
     * Constructor for BasicArmor.
     */
    public BasicArmor(int stageNr) {
        super(stageNr);

        // initialize base stats
        healthModifier = 2;
        strengthModifier = 0;
        defenseModifier = 3;
        agilityModifier = 0;

        // display
        rarity = "Common";
        displayName = "Knight's Armor";
    }
}