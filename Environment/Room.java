package environment;

import java.util.Random;
import java.util.Vector;

import actors.Actor;
import actors.Door;
import actors.Monster;
import actors.Treasure;
import actors.Player;
import actors.*;

interface Paintable {
    String getType();
}

/**
 * Class for Room objects which together create the environment in
 * which the game takes place.
 */
public abstract class Room implements Paintable {

    private static final int ROOM_SIZE = 9; // The dimension size of a room.   
    public Tile[][] tileSet;
    public Tile special;

    // stage number
    int stageNumber;

    // list of all monsters in the room
    public Vector<Monster> monsterList;

    // variables used in traverse algorithm
    private int roomNumber;
    boolean northBranchVisit;
    boolean eastBranchVisit;
    boolean southBranchVisit;
    boolean westBranchVisit;

    //Pointers to other rooms in the general 4 directions.
    Room northRoom;
    Room eastRoom;
    Room westRoom;
    Room southRoom;

    // Room generation algorithm variables.
    double treasureSpawnModifier;
    double enemySpawnModifier;

    // Random object used in generation.
    Random random;

    /**
     * Constructor for the room class. 
     * 
     * @param nr The number of the room. The variable is used in stage generation.
     */
    Room(int nr, int stageNr) {

        this.roomNumber = nr;
        this.stageNumber = stageNr;
        tileSet = new Tile[ROOM_SIZE][ROOM_SIZE];
        northBranchVisit = eastBranchVisit = southBranchVisit = westBranchVisit = false;
        random = new Random();
        
        monsterList = new Vector<>(50);
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

    /**
     * Method to generate rooms.
    */
    public void generateRoom() {

        fillWithEmptyTiles();
        fillWithWalls();

        // math => number of enemies/treasure in the room
        int numberOfEnemies = (int) Math.floor((stageNumber) * enemySpawnModifier);
        int numberOfTreasure = (int) Math.floor((stageNumber) * treasureSpawnModifier);
        // etc.

        // generate enemies and treasure

        generateEnemies(numberOfEnemies);
        generateTreasure(numberOfTreasure);

        applySpecial();
        generateDoors();
    }

    public void fillWithEmptyTiles() {

        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                tileSet[i][j] = new Tile(i, j);
            }
        }
    }

    public void fillWithWalls() {
         // line the outside of the room with walls
        for (int i = 0; i < Room.ROOM_SIZE; i++) {
            tileSet[i][0] = new Wall(i, 0);
        }
        for (int i = 0; i < Room.ROOM_SIZE; i++) {
            tileSet[0][i] = new Wall(0, i);
        }
        for (int i = 0; i < Room.ROOM_SIZE; i++) {
            tileSet[ROOM_SIZE - 1][i] = new Wall(ROOM_SIZE - 1, i);
        }
        for (int i = 0; i < Room.ROOM_SIZE; i++) {
            tileSet[i][ROOM_SIZE - 1] = new Wall(i, ROOM_SIZE - 1);
        }
    }

    /**
     * Method to print a room layout in the terminal.
     */
    public void printRoom() {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (tileSet[i][j].getActor() != null) {
                    switch (tileSet[i][j].getActor().getType()) {
                        case "door" -> System.out.print(" D ");
                        case "player" -> System.out.print(" @ ");
                        case "monster" -> System.out.print(" X ");
                        case "treasure" -> System.out.print(" T ");
                        case "obelisk" -> System.out.print("[^]");
                        default -> {   
                        }
                    }
                } else if (tileSet[i][j] instanceof Wall) {
                    System.out.print("[/]");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }

    /**
     * Method to apply the special tile to a room.
     */
    public void applySpecial() {
        // apply the special tile to the center of the room
        tileSet[4][4] = special;
    }


    /**
     * Generates doors for a room, if there are any.
     */
    public void generateDoors() {
        // method will be modified once door functionality is finished
        if (hasNorthRoom()) {
            tileSet[4][0].setActor(new Door(4, 0, northRoom, "north"), this);
            tileSet[4][0].setWalkable(true);
        }
        if (hasEastRoom()) {
            tileSet[8][4].setActor(new Door(8, 4, eastRoom, "east"), this);
            tileSet[8][4].setWalkable(true);
        }
        if (hasSouthRoom()) {
            tileSet[4][8].setActor(new Door(4, 8, southRoom, "south"), this);
            tileSet[4][8].setWalkable(true);
        }
        if (hasWestRoom()) {
            tileSet[0][4].setActor(new Door(0, 4, westRoom, "west"), this);
            tileSet[0][4].setWalkable(true);
        }
    }

    public void generateEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; i++) {
            int j = random.nextInt(1, 7);
            int k = random.nextInt(1, 7);

            if (tileSet[j][k].getActor() != null) {
                i--;
            } else {
                tileSet[j][k].setActor(new Monster("monster", j, k, stageNumber), this);
                monsterList.add((Monster) tileSet[j][k].occupant);
            }
        }
    }

    public void generateTreasure(int numberOfTreasure) {
        for (int i = 0; i < numberOfTreasure; i++) {
            int j = random.nextInt(2, 6);
            int k = random.nextInt(2, 6);

            // check if there is something already on the
            // coordinates, or they are the center coordinates
            if (tileSet[j][k].getActor() != null || (k == 4 && j == 4)) {
                // if yes, do nothing but do not count this toward the loop
                i--;
            } else {
                tileSet[j][k].setActor(new Treasure(j, k, stageNumber), this);

            }
        }
    }

    /**
     * For each monster in the room, update its stats.
     */
    public void updateMonsterStats() {
        for (Monster m : monsterList) {
            m.updateStats();
        }
    }

    /**
     * 
     */
    public void doMonstersTurn(Player player) {
        int playerX = player.getPositionX();
        int playerY = player.getPositionY();

        boolean horizontalMoveable = true;
        boolean verticalMoveable = true;
        
        for (Monster currentMonster : monsterList) {
            // lock movement
            horizontalMoveable = !(playerX == currentMonster.getPositionX());
            verticalMoveable = !(playerY == currentMonster.getPositionY());

            if (currentMonster.getHealthPoints() <= 0) {
                continue;
            }

            // if moveable in both axes, pick one at random
            if (horizontalMoveable && verticalMoveable) {
                double i = Math.random();
                horizontalMoveable = i < 0.5;
                verticalMoveable = i >= 0.5;
            }

            // right movement
            if (horizontalMoveable && currentMonster.getPositionX() < playerX) {
                currentMonster.moveRight();
            }

            // left movement
            if (horizontalMoveable && currentMonster.getPositionX() > playerX) {
                currentMonster.moveLeft();
            }
            
            // down movement
            if (verticalMoveable && currentMonster.getPositionY() < playerY) {
                currentMonster.moveDown();
            }

            // upwards movement
            if (verticalMoveable && currentMonster.getPositionY() > playerY) {
                currentMonster.moveUp();
            }
        }
        
    }

    // UTILITY BOOLEAN METHODS

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

    // Utility : GET AND SET METHODS
    
    int getRoomNumber() {
        return roomNumber;
    }

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

    /**
     * Constructor for the StartRoom.
     */
    StartRoom(int nr, int stageNr, Player p) {
        super(nr, stageNr);
        special = new Tile(4, 4);
        special.occupant = p;
        special.occupant.setPositionX(4);
        special.occupant.setPositionY(4);

        special.occupant.currentRoom = this;

    }

    @Override
    public String getType() {
        return "start";
    }
}

class DungeonRoom extends Room {

    DungeonRoom(int nr, int stageNr) {
        super(nr, stageNr);

        enemySpawnModifier = 1.0;
        treasureSpawnModifier = 0.01 * stageNumber;
        special = new Tile(4, 4);

    }

    @Override
    public String getType() {
        return "dungeon";
    }
}

class TreasureRoom extends Room {

    TreasureRoom(int nr, int stageNr) {
        super(nr, stageNr);
        special = new Tile(4, 4);
        special.occupant = new Treasure(4, 4, stageNumber);
        special.occupant.currentRoom = this;

        enemySpawnModifier = 0.0;
        treasureSpawnModifier = 1.0;
    }

    @Override
    public String getType() {
        return "treasure";
    }
}

class EndRoom extends Room {

    EndRoom(int nr, int stageNr) {
        super(nr, stageNr);
        special = new Tile(4, 4);
        special.occupant = new Obelisk(4, 4);
        special.occupant.currentRoom = this;

    }

    @Override
    public String getType() {
        return "end";
    }
}