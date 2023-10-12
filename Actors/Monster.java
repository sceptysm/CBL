package Actors;

import java.util.Random;

public class Monster extends Actor {

    Random random = new Random();

    public Monster() {

        //arbitrary position
        positionX = 1;
        positionY = 1;

        healthPoints = 5;
        strength = 1;
        defense = 1;
        agility = 1;
        
        coins = random.nextInt(1, 9);

    }
}