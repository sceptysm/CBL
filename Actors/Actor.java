package Actors;

import java.util.Vector;
import Items.Item; //Might cause issues when different types of items are introduced. 

/**
 *  Parent object for PC and NPCs.
 */
public class Actor {
    int positionX;
    int positionY;

    //in game stats
    public int healthPoints;
    public int strength;
    public int defense;
    public int agility;
    public int coins;

    //in game inventory of items
    public Vector<Item> inventory = new Vector<>();


    //initialize a representation for the actor (such as a sprite).

    // Movement methods for each Actor. 
    public void moveUp() {
        positionY += 1;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);

    }

    public void moveDown() {
        positionY -= 1;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);
    }

    public void moveLeft() {
        positionX -= 1;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);
    }

    public void moveRight() {
        positionX += 1;

        System.out.println("this actor has Y: " + this.positionY);
        System.out.println("this actor has X: " + this.positionX);
    }
}
