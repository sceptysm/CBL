package environment;

import actors.Actor;
import actors.Monster;

interface Paintable {
    String getType();
}


/**
 * Class for Room objects which together create the environment in
 * which the game takes place.
 */
public abstract class Room implements Paintable {

    private static final int ROOM_SIZE = 7; // The dimension size of a room.   
    public Tile[][] tileSet;

    // variables used in traverse algorithm
    private int roomNumber;
    public boolean northBranchVisit;
    boolean eastBranchVisit;
    boolean southBranchVisit;
    boolean westBranchVisit;

    //Pointers to other rooms in the general 4 directions.
    Room northRoom;
    Room eastRoom;
    Room westRoom;
    Room southRoom;

    /**
     * Constructor for the room class. 
     * 
     * @param nr The number of the room. The variable is used in stage generation.
     */
    Room(int nr) {

        this.roomNumber = nr;
        tileSet = new Tile[ROOM_SIZE][ROOM_SIZE];
        northBranchVisit = eastBranchVisit = southBranchVisit = westBranchVisit = false;

        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                tileSet[i][j] = new Tile();
            }
        }

    }

    int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Method that returns the actor at a given location. 
     * The "in front of" is determined by the passed arguments.
     * 
     * @param posX Index X or row of the tile to check.
     * @param posY Index Y or column of the tile to check.
     * @return an Actor if the checked tile has one, Null if there is none.
     */
    public Actor getInFrontOf(int posX, int posY) {
 
        //If there is an occupant (something on a tile)
        if (tileSet[posX][posY].occupant != null) {
            return tileSet[posX][posY].occupant;
        } else { //If there is nothing
            return null;
        }
    
    }

    // Utility boolean method 

    /**
     * A method that returns a boolean value based on whether the
     * called object has a northRoom node linked to it.
     * 
     * @return true if it has a north room, false if otherwise.
     */
    public boolean hasNorthRoom() {
        if (northRoom != null) {
            return true;
        }
        return false;
    }

    /**
     * A method that returns a boolean value based on whether the
     * called object has a eastRoom node linked to it.
     * 
     * @return true if it has a east room, false if otherwise.
     */
    public boolean hasEastRoom() {
        if (eastRoom != null) {
            return true;
        }
        return false;
    }

    /**
     * A method that returns a boolean value based on whether the
     * called object has a southRoom node linked to it.
     * 
     * @return true if it has a south room, false if otherwise.
     */
    public boolean hasSouthRoom() {
        if (southRoom != null) {
            return true;
        }
        return false;
    }

    /**
     * A method that returns a boolean value based on whether the
     * called object has a westRoom node linked to it.
     * 
     * @return true if it has a west room, false if otherwise.
     */
    public boolean hasWestRoom() {
        if (westRoom != null) {
            return true;
        }
        return false;
    }

    // Utility : Get and Set Methods

    public Tile[][] getTileSet() {
        return tileSet;
    }

    public static int getRoomSize() {
        return ROOM_SIZE;
    }

    public Room getNorthRoom() {
        return northRoom;
    }

    public Room getEastRoom() {
        return eastRoom;
    }

    public Room getSouthRoom() {
        return southRoom;
    }

    public Room getWestRoom() {
        return westRoom;
    }

}















// Children Classes of the Room Class.

class StartRoom extends Room {

    StartRoom(int nr) {
        super(nr);
        tileSet[5][6].occupant = new Monster("monster");

    }

    @Override
    public String getType() {
        return "start";
    }
}

class DungeonRoom extends Room {

    DungeonRoom(int nr) {
        super(nr);

        // Initialize number of monster based on stage number 

        int numOfMonsters = 1;

        // Assign monsters to tiles.
        tileSet[5][6].occupant = new Monster("monster");
    }

    @Override
    public String getType() {
        return "dungeon";
    }
}

class TreasureRoom extends Room {

    TreasureRoom(int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "treasure";
    }
}

class EndRoom extends Room {

    EndRoom(int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "end";
    }
}