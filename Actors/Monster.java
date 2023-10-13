package Actors;

public class Monster extends Actor {
    
    int level;
    int experiencePointWorth;

    public Monster(int posX, int posY) {
        super(posX, posY);

        //stats initialization
        healthPoints = 5;
        strength = 1;
        defense = 1;
        agility = 1;
        
        coins = random.nextInt(1, 9);
    }
}