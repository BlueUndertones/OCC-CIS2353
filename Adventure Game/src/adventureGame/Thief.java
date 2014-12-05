package adventureGame;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DeMario
 */
public class Thief extends Player implements character
{
    private final int NUMBER_OF_ROLLS = 6; 
    private final int NUMBER_OF_SIDES = 3;   
    private final int PLUS_ONE = 1;
    private final int MINUS_ONE = -1;
    
    public Thief( String name )
    {
        super( name ); 
        this.setStrength ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES) + PLUS_ONE );
        this.setDexterity ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setIntelligence ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) + MINUS_ONE);
        this.setHitPoints ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) + PLUS_ONE );
    }
    
	

    public String toString()
    {
        String stats = "<html>Thief: " + this.getName() +
                        " \n<br>Hit Points: " + this.getHitPoints()+
                        " \n<br>Intelligence: " + this.getIntelligence() +
                        " \n<br>Dexterity: " + this.getDexterity() + 
                        " \n<br>Strength: " + this.getStrength()+
                        "\n<br>-----------------------------</html>";
        return stats;
    }
 
}
