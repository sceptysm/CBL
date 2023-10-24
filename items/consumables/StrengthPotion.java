package items.consumables;

/**
 * Increases the permanent strength of the player.
 */
public class StrengthPotion extends Consumable {

    /**
     * Constructor.
     */
    public StrengthPotion(int stageNr) {
        super(stageNr);

        // TODO balancing
        potency = 1;
    }
}
