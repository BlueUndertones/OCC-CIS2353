package adventureGame;

/**
 *
 * @author DeMario
 */
public class NPC extends Player implements character
{
    private final int NUMBER_OF_ROLLS = 6; 
    private final int NUMBER_OF_SIDES = 3;   
    
    public NPC( String name )
    {
        super( name ); 
        this.strength = numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES);
        this.dexterity = numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES );
        this.intelligence = numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES );
        this.strength = numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES );
        this.hitPoints = numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES );
    }
    
     public String toString()
    {
        String stats = "NPC: " + name +
                        " \nHit Points: " + hitPoints +
                        " \nIntelligence: " + intelligence +
                        " \nDexterity: " + dexterity + 
                        " \nStrength: " + strength +
                        " \nGold: " + gold;
        return stats;
    }
 
}
