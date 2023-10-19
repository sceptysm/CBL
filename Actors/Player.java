package actors;

import java.util.Vector;

import items.Item;

public class Player extends Actor {


    public Player(String t, int posX, int posY) {
        super(t, posX, posY);
        

        healthPoints = 20;
        strength = 1;
        defense = 1;
        agility = 1;
        inventory = new Vector<Item>();

        System.out.println("Player HP: " + healthPoints);
        System.out.println("Player Strength: " + strength);
    }


}
