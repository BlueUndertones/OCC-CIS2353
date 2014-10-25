/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advemturegame;

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
