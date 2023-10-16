import gui.GamePanel;
import javax.swing.JFrame;

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


    }

}
