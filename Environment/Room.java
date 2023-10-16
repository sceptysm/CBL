package environment;

import actors.Actor;

interface Paintable {
    String getType();
}


/**
 * Class for Room objects which together create the environment in
 * which the game takes place.
 */
public abstract class Room implements Paintable {

    private static final int ROOM_SIZE = 7; // The dimension size of a room.   
    Tile[][] tileSet;

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

    public Tile[][] getTileSet() {
        return tileSet;
    }

    public int getRoomSize() {
        return ROOM_SIZE;
    }

}















// Children Classes of the Room Class.

class StartRoom extends Room {

    StartRoom(int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "start";
    }
}

class DungeonRoom extends Room {

    DungeonRoom(int nr) {
        super(nr);
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