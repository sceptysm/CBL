package Actors;

import Items.Item; //Might cause issues when different types of items are introduced. 
import java.util.Random;
import java.util.Vector;

import Environment.Tile;

/**
 *  Parent object for PC and NPCs.
 */

public class Actor extends Tile {
    //fields used for game generation.
    Random random = new Random();

    //in game stats
    public int healthPoints;
    public int strength;
    public int defense;
    public int agility;
    public int coins;

    //in game inventory of items
    public Vector<Item> inventory;

    Actor (int posX, int posY){
        super(posX, posY);
    }

    //initialize a representation for the actor (such as a sprite).

    // Movement methods for each Actor. 
    public void moveUp() {
        positionY += 1;
    }

    public void moveDown() {
        positionY -= 1;
    }

    public void moveLeft() {
        positionX -= 1;
    }

    public void moveRight() {
        positionX += 1;
    }
}
