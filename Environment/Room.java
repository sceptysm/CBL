package Environment;

import javax.xml.parsers.DocumentBuilder;

interface Paintable {
    String getType();
}


/**
 * Class for Room objects which together create the environment in
 * which the game takes place.
 */
public abstract class Room implements Paintable{

    private static final int ROOM_SIZE = 9; // The dimension size of a room.   
    private static Tile[][] tileSet = new Tile[ROOM_SIZE][ROOM_SIZE];

    // variables used in traverse algorithm
    private int roomNumber;
    public boolean northBranchVisit, eastBranchVisit, southBranchVisit, westBranchVisit;

    //Pointers to other rooms in the general 4 directions.
    Room northRoom;
    Room eastRoom;
    Room westRoom;
    Room southRoom;

    Room (int nr) {
        this.roomNumber = nr;
        northBranchVisit = eastBranchVisit = southBranchVisit = westBranchVisit = false;
    }

    int getRoomNumber(){
        return roomNumber;
    }

    /**
     *  Method that paints the room.
     */

    void paintRoom() {

        //Iterate through the tileSet array and paint each tile.
        for (int i = 0; i < tileSet.length; i++) {
            for (int j = 0; j < tileSet[i].length; j++) {
                tileSet[i][j].paintTile(i, j);
            }
        }
    }
}

class StartRoom extends Room {

    StartRoom (int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "start";
    }
}

class DungeonRoom extends Room {

    DungeonRoom (int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "dungeon";
    }
}

class TreasureRoom extends Room {

    TreasureRoom (int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "treasure";
    }
}

class EndRoom extends Room {

    EndRoom (int nr) {
        super(nr);
    }

    @Override
    public String getType() {
        return "end";
    }
}