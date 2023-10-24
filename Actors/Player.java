package actors;

import java.util.Vector;

import gui.GameLoop;
import gui.GamePainter;
import items.weapons.Weapon;
import items.helms.Helm;
import items.armor.Armor;
import items.Equipment;
import items.Item;
import items.consumables.*;


public class Player extends Actor {

    int score;
    private Equipment equippedWeapon;
    private Equipment equippedArmor;
    private Equipment equippedHelm;

    // Permanent stats
    private int permanentHealth;
    private int permanentStrength;
    private int permanentDefense;
    private int permanentAgility;

    // TODO implement XP and level-up system
    private int experiencePoints;

    public Player(String t, int posX, int posY) {
        super(t, posX, posY);

        permanentHealth = healthPoints = 20;
        permanentStrength = strength = 5;
        permanentDefense = defense = 1;
        permanentAgility = agility = 1;
        inventory = new Vector<>(6);
    }

    void doSpecialInteraction(Actor interactee) {
        if (getType() == "player") {
            switch (interactee.getType()) {
                case "door" -> {
                    currentRoom = ((Door) interactee).nextRoom;
                    GameLoop.currentRoom = currentRoom;
                    updatePositionInNewRoom(interactee);
                }
                case "monster" -> {
                    int coinsToTake = interactee.giveCoins();
                    GamePainter.addMessage("You loot the monster and find " 
                        + coinsToTake + " coins.");
                    coins += coinsToTake;
                    score += 100;
                    interactee.deleteFromCurrentTile();
                    currentRoom.monsterList.remove(interactee);
                }
                case "treasure" -> {
                    if (!interactee.inventoryIsEmpty() && inventory.size() < 6) {
                        inventory.add(((Treasure) interactee).give());
                    } else {
                        GamePainter.addMessage("Your inventory is full.");
                    }
                }
                case "obelisk" -> {
                    //generate new stage
                    GameLoop.newStage();
                    GamePainter.addMessage("You traversed to a new stage!");
                    score += 1000 * (GameLoop.getStageNumber() - 1);
                    updateRenderPosition();
                    //update Rendering

                }
                default -> {
                }
            }
        }
        
    }

    /**
     * Method to equip an item, which then calls the addEquipmentStats method.
     */
    public void equipItem(Equipment equipment) {

        if (equipment instanceof Weapon) {
            equippedWeapon = equipment;
        } else if (equipment instanceof Armor) {
            equippedArmor = equipment;
        } else if (equipment instanceof Helm) {
            equippedHelm = equipment;
        }

        addEquipmentStats(equipment);
    }

    /**
     * Method that appends the equipment stats onto the permanent stats of the player.
     */
    public void addEquipmentStats(Equipment e) {
        healthPoints = permanentHealth + e.getHealth();
        strength = permanentStrength + e.getStrength();
        defense = permanentDefense + e.getDefense();
        agility = permanentAgility + e.getAgility();
    }

    /** 
     * Use a consumable c.
    */
    void useConsumable(Consumable c) {

        int p = c.getPotency();

        if (c instanceof MaxHealthPotion) {
            permanentHealth += p;
            healthPoints += p;
        } else if (c instanceof StrengthPotion) {
            permanentStrength += p;
            strength += p;
        } else if (c instanceof DefensePotion) {
            permanentDefense += p;
            defense += p;
        } else if (c instanceof AgilityPotion) {
            permanentAgility += p;
            agility += p;
        } else if (c instanceof QuickHealthPotion) {
            healthPoints += p;
        } else if (c instanceof QuickExperiencePotion) {
            experiencePoints += p;
        }

        if (healthPoints > getMaxHP()) {
            healthPoints = getMaxHP();
        }
    }

    public void sellItem(Item item) {
        // sell the item for coins
    }

    public int getScore() {
        return score;
    }

    
    /**
     * Get the maximum possible HP the player can have at the given moment.
     */
    public int getMaxHP() {
        int maxHP = permanentHealth;
        maxHP += equippedWeapon.getHealth();
        maxHP += equippedArmor.getHealth();
        maxHP += equippedHelm.getHealth();
        return maxHP;
    }
}
