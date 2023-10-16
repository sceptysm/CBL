package environment;

import actors.Actor;
import actors.Monster;
import actors.Player;
import java.util.Random;
import java.util.Vector;

/**
 * Class that records the entirety of the map
 * for a Stage. A Stage is a collection of rooms.
 */

public class Stage {

    //number the stage is currently at. This number should be used to progressively make
    //enemies tougher and drop stronger loot (it should act as a stat modifier) and 
    //the main determinant of amount of rooms inside of a single stage.

    int stageNumber;
    int currentNumberOfRooms;
    int roomCapacity;
    Room startRoom; 
    Room endRoom;
    public Player player;
    public Actor testMonster;
    private Random random;
    private boolean hasEnd;


    // Test? 
    private Vector<Room> test;

    /**
     * Constructor for the stage object when the game commences.
     * 
     */
    public Stage() {

        stageNumber = 1; // number of the stage

        
        

        // ALGORITHM VARIABLES
        roomCapacity = 2 + stageNumber; // total amount of rooms in the stage
        currentNumberOfRooms = 1; // variable used for generateStage() algorithm
        this.hasEnd = false; // hasEnd check used in algorithm

        // initalize the stage with just a startroom
        this.startRoom = new StartRoom(currentNumberOfRooms);
        // initialize Random
        this.random = new Random();

        // initialize player in the start room. 
        player = new Player();
        player.currentRoom = startRoom;
        testMonster = startRoom.tileSet[5][6].occupant;
        testMonster.currentRoom = startRoom;
        startRoom.tileSet[player.getPositionX()][player.getPositionY()].occupant = player;

        
        // initialize the test vector, used for testing the algorithm
        this.test = new Vector<>();
        generateStage(startRoom);
    }

    /**
     * Constructor for the stage object when it is invoked 
     * as a next stage rather than the first stage, in other words,
     * when the player already exists. REWRITE
     * 
     * @param stageNr the number of the stage
     * @param p the player actor object
     */
    public Stage(int stageNr, Player p) {

        stageNumber = stageNr; // number of the stage


        // ALGORITHM VARIABLES
        roomCapacity = 2 + stageNumber; // total amount of rooms in the stage
        currentNumberOfRooms = 1; // variable used for generateStage() algorithm
        this.hasEnd = false; // hasEnd check used in algorithm

        // initalize the stage with just a startroom
        this.startRoom = new StartRoom(currentNumberOfRooms);
        // initialize Random
        this.random = new Random();

        // initialize player in the start room. 
        player = p;
        player.currentRoom = startRoom;
        startRoom.tileSet[player.getPositionX()][player.getPositionY()].occupant = player;



        // initialize the test vector, used for testing the algorithm
        this.test = new Vector<>();
        generateStage(startRoom);


    }







    // Stage generation methods : 

    // method to print out all elements of the test vector
    public void printTest() {
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.elementAt(i).getType() + test.elementAt(i).getRoomNumber());
        }
    }

    // stage generation algorithm that generates a 4-way linked list of rooms
    public void generateStage (Room currentRoom) {
        currentNumberOfRooms++;
        test.add(currentRoom);

        // which doors are to be generated?
        boolean genNorth = random.nextBoolean();
        boolean genEast = random.nextBoolean();
        boolean genSouth = random.nextBoolean();
        boolean genWest = random.nextBoolean();

        // run a check to see which door you "came" from
        if (currentRoom.northRoom != null) {
            genNorth = false;
        }
        if (currentRoom.eastRoom != null) {
            genEast = false;
        }
        if (currentRoom.southRoom != null) {
            genSouth = false;
        }
        if (currentRoom.westRoom != null) {
            genWest = false;
        }

        // if algorithm is at the end, generate a single end room
        if (currentNumberOfRooms > roomCapacity - 1) {
            Room end = new EndRoom(currentNumberOfRooms);

            if (currentRoom.northRoom != null && !hasEnd) {
                currentRoom.southRoom = end;
                currentNumberOfRooms++;
                test.add(currentRoom.southRoom);
                hasEnd = true;
            }
            if (currentRoom.eastRoom != null && !hasEnd) {
                currentRoom.westRoom = end;
                currentNumberOfRooms++;
                test.add(currentRoom.westRoom);
                hasEnd = true;
            }
            if (currentRoom.southRoom != null && !hasEnd) {
                currentRoom.northRoom = end;
                currentNumberOfRooms++;
                test.add(currentRoom.northRoom);
                hasEnd = true;
            }
            if (currentRoom.westRoom != null && !hasEnd) {
                currentRoom.eastRoom = end;
                currentNumberOfRooms++;
                test.add(currentRoom.eastRoom);
                hasEnd = true;
            }

            //override booleans so algorithm doesnt continue
            genNorth = genEast = genSouth = genWest = false;
        }
        
        // iff algorithm is neither at the beginning nor the end
        Room tempRoom;
        double randomVariable;

        // recursive part of algorithm
        if (genNorth) {
            //generate a random variable that determines what type of room is to be generated
            randomVariable = Math.random();
            if (randomVariable < 0.2) {
                tempRoom = new TreasureRoom(currentNumberOfRooms);
            } else {
                tempRoom = new DungeonRoom(currentNumberOfRooms);
            }
            currentRoom.northRoom = tempRoom;
            currentRoom.northRoom.southRoom = currentRoom;
            generateStage(currentRoom.northRoom);
        }
        if (genEast) {
            randomVariable = Math.random();
            if (randomVariable < 0.2) {
                tempRoom = new TreasureRoom(currentNumberOfRooms);
            } else {
                tempRoom = new DungeonRoom(currentNumberOfRooms); 
            }

            currentRoom.eastRoom = tempRoom;
            currentRoom.eastRoom.westRoom = currentRoom;
            generateStage(currentRoom.eastRoom);
        }
        if (genSouth) {
            randomVariable = Math.random();
            if (randomVariable < 0.2) {
                tempRoom = new TreasureRoom(currentNumberOfRooms);
            } else {
                tempRoom = new DungeonRoom(currentNumberOfRooms);
            }
            currentRoom.southRoom = tempRoom;
            currentRoom.southRoom.northRoom = currentRoom;
            generateStage(currentRoom.southRoom);
        }
        if (genWest) {
            randomVariable = Math.random();
            if (randomVariable < 0.2) {
                tempRoom = new TreasureRoom(currentNumberOfRooms);
            } else {
                tempRoom = new DungeonRoom(currentNumberOfRooms);
            }

            currentRoom.westRoom = tempRoom;
            currentRoom.westRoom.eastRoom = currentRoom;
            generateStage(currentRoom.westRoom);
        }

        //recursion so that algorithm doesn't die - in the case that all 4 doors are "false"
        if (currentNumberOfRooms <= roomCapacity - 1) {
            test.remove(currentRoom);
            currentNumberOfRooms--;
            generateStage(currentRoom);
        }
    }

    public void traverseStage (Room currentRoom){
        System.out.println("NUMBER: " + currentRoom.getRoomNumber());
        System.out.println("TYPE: " + currentRoom.getType());
        String neighbors = "";

        if (currentRoom.northRoom != null) {
            neighbors += "NORTH: " + currentRoom.northRoom.getType() + " ";
        }
        if (currentRoom.eastRoom != null) {
            neighbors += "EAST: " + currentRoom.eastRoom.getType() + " ";
        }
        if (currentRoom.southRoom != null) {
            neighbors += "SOUTH: " + currentRoom.southRoom.getType() + " ";
        }
        if (currentRoom.westRoom != null) {
            neighbors += "WEST: " + currentRoom.westRoom.getType() + " ";
        }

        System.out.println(neighbors);

        if (currentRoom.northRoom != null && !currentRoom.northBranchVisit) {
            currentRoom.northBranchVisit = true;
            traverseStage(currentRoom.northRoom);
        }
        if (currentRoom.eastRoom != null && !currentRoom.eastBranchVisit) {
            currentRoom.eastBranchVisit = true;
            traverseStage(currentRoom.eastRoom);
        }
        if (currentRoom.southRoom != null && !currentRoom.southBranchVisit) {
            currentRoom.southBranchVisit = true;
            traverseStage(currentRoom.southRoom);
        }
        if (currentRoom.westRoom != null && !currentRoom.westBranchVisit) {
            currentRoom.westBranchVisit = true;
            traverseStage(currentRoom.westRoom);
        }
    }

    public Room getStartRoom() {
        return startRoom;
    }
}
