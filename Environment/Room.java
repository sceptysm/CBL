package Environment;
import Actors.*;
import java.util.Random;

interface Paintable {
    String getType();
}

/**
 * Class for Room objects which together create the environment in
 * which the game takes place.
 */

public abstract class Room implements Paintable {

    private static final int ROOM_SIZE = 9; // The dimension size of a room.   
    public Tile[][] tileSet = new Tile[ROOM_SIZE][ROOM_SIZE];
    public Tile special;

    // variables used in traverse algorithm
    private int roomNumber;
    public boolean northBranchVisit, eastBranchVisit, southBranchVisit, westBranchVisit;

    // Pointers to other rooms in the general 4 directions.
    Room northRoom;
    Room eastRoom;
    Room westRoom;
    Room southRoom;

    // Room generation algorithm variables
    double treasureSpawnModifier, enemySpawnModifier;

    // random
    Random random;

    Room (int nr) {
        this.roomNumber = nr;
        northBranchVisit = eastBranchVisit = southBranchVisit = westBranchVisit = false;
        random = new Random();
    }

    // GETTERS/SETTERS

    int getRoomNumber(){
        return roomNumber;
    }

    public void generateRoom (int stageNumber){
        // line the outside of the room with walls
        for (int i = 0; i < Room.ROOM_SIZE; i++){
            tileSet[i][0] = new Wall(i, 0);
        }
        for (int i = 0; i < Room.ROOM_SIZE; i++){
            tileSet[0][i] = new Wall(0, i);
        }
        for (int i = 0; i < Room.ROOM_SIZE; i++){
            tileSet[ROOM_SIZE - 1][i] = new Wall(ROOM_SIZE - 1, i);
        }
        for (int i = 0; i < Room.ROOM_SIZE; i++){
            tileSet[i][ROOM_SIZE - 1] = new Wall(i, ROOM_SIZE - 1);
        }

        // spawn enemies/treasure/actors - NUMBERS SUBJECT TO CHANGE
        int numberOfEnemies = (int)Math.floor((1 + stageNumber)*this.enemySpawnModifier);
        int numberOfTreasure = (int)Math.floor((1 + stageNumber)*this.treasureSpawnModifier);
        // etc.

        // generate enemies
        for (int i = 0; i < numberOfEnemies; i++){
            int j = random.nextInt(1, 9);
            int k = random.nextInt(1, 9);

            if (tileSet[j][k] != null){
                i--;
            } else {
                tileSet[j][k] = new Monster(j, k);
            }
        }

        // generate treasure
        for (int i = 0; i < numberOfTreasure; i++){
            int j = random.nextInt(1, 9);
            int k = random.nextInt(1, 9);

            // check if there is something already on the coordinates or they are the center coordinates
            if (tileSet[j][k] != null || (k == 5 && j == 5)){
                // if yes, do nothing but do not count this toward the loop
                i--;
            } else {
                tileSet[j][k] = new Treasure(j, k);
            }
        }

        applySpecial();
        generateDoors();
    }

    public void applySpecial () {
        // apply the special tile to the center of the room
        tileSet[4][4] = this.special;
    }

    public void generateDoors () {
        // method will be modified once door functionality is finished
        if (northRoom != null) {
            tileSet[0][4] = new Door(0, 4);
        }
        if (eastRoom != null) {
            tileSet[4][8] = new Door(4, 8);
        }
        if (southRoom != null) {
            tileSet[8][4] = new Door(8, 4);
        }
        if (westRoom != null) {
            tileSet[4][0] = new Door(4, 0);
        }
    }

    public void printRoom () {
        for (int i = 0; i < Room.ROOM_SIZE; i++){
            for (int j = 0; j < Room.ROOM_SIZE; j++){
                if (tileSet[i][j] instanceof Wall){
                    System.out.print("[/]");
                }
                else if (tileSet[i][j] instanceof Door){
                    System.out.print("| |");
                }
                else if (tileSet[i][j] instanceof Player){
                    System.out.print(" @ ");
                }
                else if (tileSet[i][j] instanceof Monster){
                    System.out.print(" X ");
                }
                else if (tileSet[i][j] instanceof Treasure){
                    System.out.print(" T ");
                }
                else if (tileSet[i][j] instanceof Obelisk){
                    System.out.print("[^]");
                }
                else if (tileSet[i][j] == null) {
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
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


// here you can modify certain attributes of each room

class StartRoom extends Room {

    StartRoom (int nr) {
        super(nr);
        special = new Player(4, 4);

        // set up modifiers for a given room
        enemySpawnModifier = 0.0;
        treasureSpawnModifier = 1.0;
        // etc.
    }

    @Override
    public String getType() {
        return "start";
    }
}

class DungeonRoom extends Room {

    DungeonRoom (int nr) {
        super(nr);
        special = new Treasure(4, 4);

        // modifiers
        enemySpawnModifier = 1.0;
        treasureSpawnModifier = 1.0;
    }

    @Override
    public String getType() {
        return "dungeon";
    }
}

class TreasureRoom extends Room {

    TreasureRoom (int nr) {
        super(nr);
        special = new Monster(4, 4);

        // modifiers
        enemySpawnModifier = 0.0;
        treasureSpawnModifier = 1.0;
    }

    @Override
    public String getType() {
        return "treasure";
    }
}

class EndRoom extends Room {

    EndRoom (int nr) {
        super(nr);
        special = new Obelisk();

        //modifiers
        enemySpawnModifier = 0.0;
        treasureSpawnModifier = 0.0;
    }

    @Override
    public String getType() {
        return "end";
    }
}