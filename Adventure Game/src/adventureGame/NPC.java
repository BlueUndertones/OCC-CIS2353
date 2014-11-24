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
public class NPC extends Player implements character
{
    private final int NUMBER_OF_ROLLS = 6; 
    private final int NUMBER_OF_SIDES = 3;   
    
    public NPC( String name )
    {
        super( name ); 
        this.setStrength ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES) );
        this.setDexterity ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setIntelligence ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setStrength ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setHitPoints ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
    }
    
    public NPC ( )
    {
    	Die dice = new Die(6);
		dice.roll(); 
		int value = dice.getValue(); 
		this.setName( getNpcName(value) );
    	this.setStrength ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES) );
        this.setDexterity ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setIntelligence ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setStrength ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setHitPoints ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        dice.roll();
        value = dice.getValue();
        this.setWeapon(value);
    }
	public static String getNpcName(int npcValue )
	{
		String npcName = "";
		
		switch ( npcValue )
		{
		
			case 1:
			{
				npcName = "Small Monster";
				break;	
			}
		
			case 2:
			{
				npcName = "Goblin";
				break;	
			}
			case 3:
			{
				npcName = "Dragon";
				break;	
			}
			
			case 4:
			{
				npcName = "Hobbit";
				break;	
			}
			case 5:
			{
				npcName = "Witch";
				break;
			}		
			default:
			{
				
			}
		}
		return npcName;
	}

    public String toString()
    {
        String stats = "<html>NPC: " + this.getName() +
                        " \n<br>Hit Points: " + this.getHitPoints()+
                        " \n<br>Intelligence: " + this.getIntelligence() +
                        " \n<br>Dexterity: " + this.getDexterity() + 
                        " \n<br>Strength: " + this.getStrength()+
                        "\n<br>-----------------------------</html>";
        return stats;
    }
 
}
