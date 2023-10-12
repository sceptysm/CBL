import javax.swing.JFrame;
import Actors.*;


/**
 * Test method for draft classes and interactions.
 */
public class Main {


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("CBL Roguelike");
        GamePanel panel = new GamePanel();
    

        frame.add(panel);
        frame.pack(); // pack() adjusts the size of the frame dynamically based on its components
 
        frame.setLocationRelativeTo(null); //Displayed at center of the screen
        frame.setVisible(true);
        

        
        Player player = new Player();
        Monster monster =  new Monster();

        player.moveUp();
        player.moveRight();
        player.moveLeft();

        Combat encounter = new Combat(player, monster);
        encounter.attack();
        
    }

}
