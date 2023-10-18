package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Class that extends JPanel to used to display the game.
 * Template screen settings for a 4:3. Maths to determine our 
 * own size of tiles and visuals are to still to be implemented.
 * 
*/
public class GamePanel extends JPanel implements ActionListener {

    // Screen Configuration
    final int originalTileSize = 8;
    final int sizeScaler = 6; // Scales pixelated graphics
    final int actualTileSize = originalTileSize * sizeScaler;

    // 4 : 3 Ratio - Number of Tiles Rendered at a time
    final int maxTilesColumn = 16;
    final int maxTilesRow = 12;

    // SCREEN SETTINGS determined by scaled up tiles and number of tiles rendered
    final int screenWidth = actualTileSize * maxTilesColumn;
    final int screenHeight = actualTileSize * maxTilesRow;


    // Input Handlers
    KeyboardHandler keyHandler = new KeyboardHandler();
    MouseHandler mouseHandler = new MouseHandler();

    // Visual Renderer
    GamePainter painter;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setFocusable(true);


        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);

        GameLoop.startGame();

        painter = new GamePainter(GameLoop.currentRoom, actualTileSize);
        GameLoop.painter = painter;

        System.out.println(actualTileSize);
        System.out.println(screenHeight);
        System.out.println(screenWidth);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Initialize Graphics g inside the painter object.
        painter.setGraphics(g);

        // Paint the screen and the game.

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);
        painter.paintRoom();
        
        // update the GameLoop based on player input.
        try { // the update() method of GameLoop utilizes Thread sleep so try/catch is needed.
            GameLoop.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
  
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}