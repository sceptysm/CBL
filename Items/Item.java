package items;

import actors.Actor;

public abstract class Item {

    Actor owner;
    int value;


    void sell() {
        owner.coins += value;
        owner.inventory.remove(this);

    } 

    
}
