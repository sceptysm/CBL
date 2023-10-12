package Items;

import Actors.Actor;

public abstract class Item {

    Actor owner;
    int value;

    //visual representation


    void sell() {
        owner.coins += value;
        owner.inventory.remove(this);

    } 

    
}
