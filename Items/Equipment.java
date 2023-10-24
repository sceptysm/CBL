package items;

/**
 * Abstract class equipment, from which all equipment inherits.
 */

public abstract class Equipment extends Item {
    // Stat modifiers
    protected int healthModifier;
    protected int strengthModifier;
    protected int defenseModifier;
    protected int agilityModifier;

    // Display
    protected String rarity;
    
    public Equipment(int stageNr) {
        super(stageNr);
    }

    @Override
    public void updateStats(int stageNr) {
        // TODO balancing
        level = stageNr;
        value += (value / 3) * stageNr;

        healthModifier += (healthModifier / 3) * stageNr;
        strengthModifier += (strengthModifier / 3) * stageNr;
        defenseModifier += (defenseModifier / 3) * stageNr;
        agilityModifier += (agilityModifier / 3) * stageNr;
    }

    public int getHealth() {
        return this.healthModifier;
    }

    public int getStrength() {
        return this.strengthModifier;
    }

    public int getDefense() {
        return this.defenseModifier;
    }

    public int getAgility() {
        return this.agilityModifier;
    }
}