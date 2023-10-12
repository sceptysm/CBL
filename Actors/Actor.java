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

    // Getters for fields of Actor.
    public int getStrength() {
        return strength;
    }
    public int getAgility() {
        return agility;
    }
    public int getDefense() {
        return defense;
    }
    public Vector<Object> getInventory() {
        return inventory;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

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
