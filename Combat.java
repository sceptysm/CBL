import Actors.*;

/**
 * Class that handles in-game combat.
 * Draft for the logic behind combat.
 * Seems generally inefficient/ineffective. 
 */
public class Combat {

    Actor attacker;
    Actor defender;

    /**
     *  Constructor.
     */
    public Combat(Actor a, Actor d) {
        attacker = a;
        defender = d;
    }
    
    /**
     * General Idea of combat. 
     * Missing evade and defense checks.
     * 
     */
    public void attack() {

        // Attack Method. 
        // Attacker attacks first.
        System.out.println("Defender now has " + defender.healthPoints + " HP");

        defender.healthPoints -= attacker.strength;
        
        System.out.println("Attacker attacks for: " + attacker.strength);
        System.out.println("Defender now has " + defender.healthPoints + " HP");

    }
    
}
