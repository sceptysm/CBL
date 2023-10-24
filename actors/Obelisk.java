package actors;

import environment.Stage;

/**
 * Obelisk class extending Actor, when interacted with transports the player to the next stage.
 */
public class Obelisk extends Actor {

    Stage currentStage;

    /**
     * Constructor.
     */
    public Obelisk(int posX, int posY) {
        super("obelisk", posX, posY);
    }
}
