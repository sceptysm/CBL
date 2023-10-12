package Environment;

/**
 * Class that records the entirety of the map
 * for a Stage. A Stage is a collection of rooms.
 * 
 */
public class Stage {

    //number the stage is currently at. This number should be used to progressively make
    //enemies tougher and drop stronger loot (it should act as a stat modifier) and 
    //the main determinant of amount of rooms inside of a single stage.

    int stageNumber;
    Room initial;
    
    
}
