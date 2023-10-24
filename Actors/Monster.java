package actors;

/**
 * Monster class.
 * TODO - make subclasses of different enemy types
 */
public class Monster extends Actor {

    int level;
    int experiencePointWorth;

    /**
     * Constructor.
     */
    public Monster(String t, int posX, int posY, int stageNr) {
        super(t, posX, posY);
        level = stageNr;

        // base stats of the monster
        healthPoints = 5;
        strength = 1;
        defense = 1;
        agility = 1;
        
        coins = random.nextInt(1, 9);
    }

    /**
     * Update the stats of the monster depending on level.
     */
    public void updateStats() {
        // TODO balancing
        healthPoints += (healthPoints / 3) * level;
        strength += (strength / 3) * level;
        defense += (defense / 3) * level;
        agility += (agility / 3) * level;

        // coins scaling
        coins += (coins / 3) * level;
    }
}
