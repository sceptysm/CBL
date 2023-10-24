package items.consumables;

/**
 * Increases the permanent defense of the player.
 */
public class DefensePotion extends Consumable {

    /**
     * Constructor.
     */
    public DefensePotion(int stageNr) {
        super(stageNr);

        // TODO balancing
        potency = 1;
    }
}