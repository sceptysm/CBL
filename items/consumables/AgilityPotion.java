package items.consumables;

/**
 * Increases the permanent agility of the player.
 */
public class AgilityPotion extends Consumable {

    /**
     * Constructor.
     */
    public AgilityPotion(int stageNr) {
        super(stageNr);

        // TODO balancing
        potency = 1;
    }
}