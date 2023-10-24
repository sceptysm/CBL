package items.consumables;

/**
 * Gives the player a boost in experience.
 */
public class QuickExperiencePotion extends Consumable {

    /**
     * Constructor.
     */
    public QuickExperiencePotion(int stageNr) {
        super(stageNr);

        // TODO balancing
        potency = 4;
    }
}