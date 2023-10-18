package actors;

import java.util.Vector;

public class Player extends Actor {


    public Player(String t) {
        super(t);
        positionX = 1;
        positionY = 3;
        updateRenderPosition();

        

    }
}
