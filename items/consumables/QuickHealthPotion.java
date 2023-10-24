package items.consumables;

/**
 * Restores the players health.
 */
public class QuickHealthPotion extends Consumable {

    /**
     * Constructor.
     */
    public QuickHealthPotion(int stageNr) {
        super(stageNr);

        // TODO balancing
        potency = 4;
    }
}