package items.consumables;

/**
 * Increases the maximum health of the player.
 */
public class MaxHealthPotion extends Consumable {

    /**
     * Constructor.
     */
    public MaxHealthPotion(int stageNr) {
        super(stageNr);

        // TODO balancing
        potency = 1;
    }
}