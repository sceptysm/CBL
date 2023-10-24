package items.consumables;

import items.Item;

/**
 * Consumable class.
 */
public abstract class Consumable extends Item {

    // Potency stat, determines the magnitude of the effect of the potion
    protected int potency;

    public Consumable(int stageNr) {
        super(stageNr);
    }

    @Override
    public void updateStats(int stageNr) {
        // TODO balancing
        level = stageNr;
        value += (value / 3) * stageNr;

        potency += (potency / 2) * stageNr;
    }

    public int getPotency() {
        return this.potency;
    }
}

