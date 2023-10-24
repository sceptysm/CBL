package items;

interface InventoryElement {
    void updateStats(int stageNr);
}

/**
 * Item class.
 */

public abstract class Item implements InventoryElement {

    protected int level;
    protected int value;

    public String displayName;

    public Item(int stageNr) {
        this.level = stageNr;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public int getLevel() {
        return this.level;
    }
}
