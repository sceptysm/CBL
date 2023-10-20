package actors;

import java.util.Vector;

import gui.GameLoop;
import items.Item;

public class Player extends Actor {

    int score;


    public Player(String t, int posX, int posY) {
        super(t, posX, posY);
        

        healthPoints = 20;
        strength = 1;
        defense = 1;
        agility = 1;
        inventory = new Vector<Item>();

        System.out.println("Player HP: " + healthPoints);
        System.out.println("Player Strength: " + strength);
    }

    void doSpecialInteraction(Actor interactee) {
        if (getType() == "player") {
            switch (interactee.getType()) {
                case "door" -> {
                    currentRoom = ((Door) interactee).nextRoom;
                    updatePositionInNewRoom(interactee);
                }
                case "monster" -> {
                    System.out.println("lootin");
                    coins = interactee.giveCoins();
                    interactee.deleteFromCurrentTile();
                }
                case "treasure" -> {

                }
                case "obelisk" -> {
                    //generate new stage
                    GameLoop.newStage();
                    score += 1000 * (GameLoop.getStageNumber() - 1);
                    //GamePainter.setCurrentRoom(currentRoom);
                    updateRenderPosition();
                    //update Rendering

                }
                default -> {
                }
            }
        }
        
    }

    public int getScore() {
        return score;
    }


}
