package Actors;

import Environment.Tile;

public class Obelisk extends Tile{
    // interactable that moves you along to the next stage

    public Obelisk (){
        // obelisk is always positioned in the center of the room
        super(4, 4);
    }
}
