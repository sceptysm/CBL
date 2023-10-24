package items.weapons;

/**
 * BasicSword class.
 */

public class BasicSword extends Weapon {

    /**
     * Constructor for BasicSword.
     */
    public BasicSword(int stageNr) {
        super(stageNr);

        // initialize base stats
        healthModifier = 0;
        strengthModifier = 5;
        defenseModifier = 1;
        agilityModifier = 1;

        // display
        rarity = "Common";
        displayName = "Knight's Sword";
    }
}