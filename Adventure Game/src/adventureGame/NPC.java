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
    }
    
    public static String getNpcName(int npcValue )
	{
		String npcName = "";
		
		switch ( npcValue )
		{
			case 0:
			{
				npcName = "Small Monster";
				break;	
			}
		
			case 1:
			{
				npcName = "Goblin";
				break;	
			}
			case 2:
			{
				npcName = "Dragon";
				break;	
			}
			
			case 3:
			{
				npcName = "Hobbit";
				break;	
			}
			case 4:
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
        String stats = "<html>NPC: " + this.getName()+
		                " \n<br>Hit Points: " + this.getHitPoints()+
		                " \n<br>Intelligence: " + this.getIntelligence() +
		                " \n<br>Dexterity: " + this.getDexterity() + 
		                " \n<br>Strength: " + this.getStrength() +
		                "\n<br>-----------------------------</html>";
        return stats;
    }
 
}
