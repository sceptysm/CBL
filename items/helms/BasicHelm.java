package items.helms;

/**
 * BasicHelm class.
 */

public class BasicHelm extends Helm {

    /**
     * Constructor for BasicHelm.
     */
    public BasicHelm(int stageNr) {
        super(stageNr);

        // initialize base stats
        healthModifier = 0;
        strengthModifier = 0;
        defenseModifier = 2;
        agilityModifier = 2;

        // display
        rarity = "Common";
        displayName = "Knight's Helm";
    }
}