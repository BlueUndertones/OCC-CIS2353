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
        String stats = "<html>NPC: " + name +
		                " \n<br>Hit Points: " + hitPoints +
//		                " \n<br>Intelligence: " + intelligence +
//		                " \n<br>Dexterity: " + dexterity + 
//		                " \n<br>Strength: " + strength +
//		                " \n<br>Gold: " + gold +
		                "\n<br>-----------------------------</html>";
        return stats;
    }
 
}
