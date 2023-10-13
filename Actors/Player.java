package actors;

import java.util.Vector;

import items.Item;

public class Player extends Actor {


    public Player() {
        positionX = 48;
        positionY = 48;

        healthPoints = 20;
        strength = 1;
        defense = 1;
        agility = 1;
        inventory = new Vector<Item>();

        System.out.println("Player HP: " + healthPoints);
        System.out.println("Player Strength: " + strength);
    }

    



    //method for attacking

    //method for taking items
}
