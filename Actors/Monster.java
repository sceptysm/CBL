package actors;

public class Monster extends Actor {

    int level;
    int experiencePointWorth;

    public Monster(String t, int posX, int posY) {

        super(t, posX, posY);
        //arbitrary position
       

        //stats initialization
        healthPoints = 5;
        strength = 1;
        defense = 1;
        agility = 1;
        
        coins = random.nextInt(1, 9);

    }
}