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

    void moveUp() {
        positionY += 1;
    }
    void moveDown() {
        positionY -= 1;
    }
    void moveLeft() {
        positionX -= 1;
    }
    void moveRight() {
        positionX += 1;
    }
}
