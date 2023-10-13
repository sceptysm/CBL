package actors;

public class Monster extends Actor {

    int level;
    int experiencePointWorth;

    public Monster() {

        //arbitrary position
        positionX = 1;
        positionY = 1;

        //stats initialization
        healthPoints = 5;
        strength = 1;
        defense = 1;
        agility = 1;
        
        coins = random.nextInt(1, 9);

    }
}