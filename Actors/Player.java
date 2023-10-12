package Actors;

import Items.Item;
import java.util.Vector;

public class Player extends Actor {


    public Player() {
        positionX = 0;
        positionY = 0;

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
