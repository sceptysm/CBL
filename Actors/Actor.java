package actors;

import java.util.Random;
import java.util.Vector;

import items.Item;

/**
 *  Parent object for PC and NPCs.
 */
public class Actor {
    //fields used for game generation.
    int positionX;
    int positionY;
    Random random = new Random();

    //in game stats
    public int healthPoints;
    public int strength;
    public int defense;
    public int agility;
    public int coins;


    //in game inventory of items
    public Vector<Item> inventory;


    //initialize a representation for the actor (such as a sprite).

    // Movement methods for each Actor. 
    public void moveUp() {
        positionY -= 16;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);

    }

    public void moveDown() {
        positionY += 16;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);
    }

    public void moveLeft() {
        positionX -= 16;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);
    }

    public void moveRight() {
        positionX += 16;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);
    }

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
}
