package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import actors.Player;


/**
 * Class that extends JPanel to used to display the game.
 * Template screen settings for a 4:3. Maths to determine our 
 * own size of tiles and visuals are to still to be implemented.
 * 
*/
public class GamePanel extends JPanel implements ActionListener {

    //Screen Configuration
    final int originalTileSize = 8;
    final int sizeScaler = 6; // Scales pixelated graphics
    final int actualtileSize = originalTileSize * sizeScaler;

    // 4 : 3 Ratio - Number of Tiles Rendered at a time
    final int maxTilesColumn = 16;
    final int maxTilesRow = 12;

    //SCREEN SETTINGS determined by scaled up tiles and number of tiles rendered
    final int screenWidth = actualtileSize * maxTilesColumn;
    final int screenHeight = actualtileSize * maxTilesRow;


    //Input Handlers
    KeyboardHandler keyHandler = new KeyboardHandler();
    MouseHandler mouseHandler = new MouseHandler();
    GameLoop gameLoop = new GameLoop(keyHandler, mouseHandler);

    //Visual

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setFocusable(true);


        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        gameLoop.update();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}