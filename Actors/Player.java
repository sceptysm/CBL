package Actors;

import java.util.Vector;

public class Player extends Actor {


    public Player() {
        positionX = 0;
        positionY = 0;

        healthPoints = 20;
        strength = 1;
        defense = 1;
        agility = 1;

        System.out.println("Player HP: " + healthPoints);
        System.out.println("Player Strength: " + strength);
    }
}
