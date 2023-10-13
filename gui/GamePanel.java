package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import actors.Player;


/**
 * Class that extends JPanel to used to display the game.
 * Template screen settings for a 4:3. Maths to determine our 
 * own size of tiles and visuals are to still to be implemented.
 * 
*/
public class GamePanel extends JPanel implements ActionListener {

    //SCREEN SETTINGS 
    final int originalTileSize = 16;
    final int sizeScaler = 3;

    final int actualtileSize = originalTileSize * sizeScaler;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = actualtileSize * maxScreenCol;
    final int screenHeight = actualtileSize * maxScreenRow;

    //final JButton newGameButton = new JButton("New Game");


    //Test Objects
    KeyboardHandler keyHandler = new KeyboardHandler();
    Player player = new Player();    


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setFocusable(true);

        //newGameButton.setBounds((screenWidth / 4) + 30, screenHeight / 2, (screenWidth / 2) - 30 , 30);
        //newGameButton.setFocusable(false);

        this.addKeyListener(keyHandler);
        //this.add(newGameButton);

        /* 
        long lastUpdateTime = System.currentTimeMillis();
        long elapsedTime;
        long desiredFrameTime = 16;

        while (true) {
            long currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - lastUpdateTime;

            if (elapsedTime >= desiredFrameTime) {
                update();
                repaint();
                lastUpdateTime = currentTime;
            }

        }
        */
    }

    public void update() {

        //If W is pressed
        if (keyHandler.keyboardRecord[KeyEvent.VK_W]) {
            player.moveUp();
        } 
        if (keyHandler.keyboardRecord[KeyEvent.VK_A]) {
            player.moveLeft();
        }  
        if (keyHandler.keyboardRecord[KeyEvent.VK_S]) {
            player.moveDown();
        }  
        if (keyHandler.keyboardRecord[KeyEvent.VK_D]) {
            player.moveRight();
        }
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(player.getPositionX(), player.getPositionY(), actualtileSize, actualtileSize);
        g.setColor(Color.BLACK);

        update();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}