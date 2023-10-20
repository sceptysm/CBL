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
    static final int ORIGINAL_TILE_SIZE = 8;
    static final int SIZE_SCALER = 6; // Scales pixelated graphics
    public static final int REAL_TILE_SIZE = ORIGINAL_TILE_SIZE * SIZE_SCALER;

    // 4 : 3 Ratio - Number of Tiles Rendered at a time
    static final int MAX_COLUMN_TILES = 17;
    static final int MAX_ROW_TILES = 17;

    // SCREEN SETTINGS determined by scaled up tiles and number of tiles rendered
    static final int SCREEN_WIDTH = REAL_TILE_SIZE * MAX_COLUMN_TILES;
    static final int SCREEN_HEIGHT = REAL_TILE_SIZE * MAX_ROW_TILES;


    // Input Handlers
    KeyboardHandler keyHandler = new KeyboardHandler();
    MouseHandler mouseHandler = new MouseHandler();

    // Visual Renderer
    GamePainter painter;


    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setFocusable(true);


        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);

        GameLoop.startGame();

        painter = new GamePainter(GameLoop.currentRoom);
        GameLoop.painter = painter;

        System.out.println(REAL_TILE_SIZE);
        System.out.println(SCREEN_HEIGHT);
        System.out.println(SCREEN_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Initialize Graphics g inside the painter object.
        painter.setGraphics(g);

        // Paint the screen and the game.

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        painter.paintGame();
        
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