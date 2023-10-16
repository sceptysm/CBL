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

    Room(int nr) {

        this.roomNumber = nr;
        tileSet = new Tile[ROOM_SIZE][ROOM_SIZE];
        northBranchVisit = eastBranchVisit = southBranchVisit = westBranchVisit = false;


    }

    int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Method that returns the actor at a given location. 
     * The "in front of" is determined by the passed arguments.
     * 
     * @param posX
     * @param posY
     * @return
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