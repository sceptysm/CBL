package actors;

public class Monster extends Actor {

    int level;
    int experiencePointWorth;

    public Monster(String t) {

        super(t);
        //arbitrary position
        positionX = 5;
        positionY = 6;
        updateRenderPosition();

        //stats initialization
        healthPoints = 5;
        strength = 1;
        defense = 1;
        agility = 1;
        
        coins = random.nextInt(1, 9);

    }
}