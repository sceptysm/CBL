package Environment;


/**
 * Class for Room objects which together create the environment in
 * which the game takes place.
 */
public abstract class Room {

    private static final int ROOM_SIZE = 8; // The dimension size of a room.   
    private static Tile[][] tileSet = new Tile[ROOM_SIZE][ROOM_SIZE];

    //Pointers to other rooms in the general 4 directions.
    Room northRoom;
    Room eastRoom;
    Room westRoom;
    Room southRoom;

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
