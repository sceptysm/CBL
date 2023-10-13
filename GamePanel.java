import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Class that extends JPanel to used to display the game.
 * Template screen settings for a 4:3. Maths to determine our 
 * own size of tiles and visuals are to still to be implemented.
 * 
*/
public class GamePanel extends JPanel{

    //SCREEN SETTINGS 
    final int originalTileSize = 16;
    final int sizeScaler = 3;

    final int actualtileSize = originalTileSize * sizeScaler;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = actualtileSize * maxScreenCol;
    final int screenHeight = actualtileSize * maxScreenRow;

    final JButton newGameButton = new JButton("New Game");

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);

        newGameButton.setBounds((screenWidth / 4) + 30, screenHeight / 2, (screenWidth / 2) - 30 , 30);
        newGameButton.setFocusable(false);
        this.add(newGameButton);
    }
}