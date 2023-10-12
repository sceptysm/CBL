import Actors.*;


/**
 * Test method for draft classes and interactions.
 */
public class Main {


    public static void main(String[] args) {

        Player player = new Player();
        Monster monster =  new Monster();

        player.moveUp();
        player.moveRight();
        player.moveLeft();

        Combat encounter = new Combat(player, monster);
        encounter.attack();
        
    }

}
