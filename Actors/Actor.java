package Actors;

import java.util.Vector;

/**
 *  Parent object for PC and NPCs.
 */
public class Actor {
    int positionX;
    int positionY;

    //in game stats
    int healthPoints;
    int strength;
    int defense;
    int agility;
    
    public int coins;

    public Vector<Object> inventory = new Vector<>();


    //initialize a representation for the actor.

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
