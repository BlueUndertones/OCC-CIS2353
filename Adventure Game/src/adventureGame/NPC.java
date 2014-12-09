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
        this.setHitPoints ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
     	Die dice = new Die(6) ;
    	dice.roll(); 
    	int value = dice.getValue(); 
        value = dice.getValue();
        this.setWeapon(value);
    }
    
    public NPC ( )
    {
    	Die dice = new Die(6) ;
		dice.roll(); 
		int value = dice.getValue(); 
		this.setName( getNpcName(value) );
    	this.setStrength ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES) );
        this.setDexterity ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setIntelligence ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        this.setHitPoints ( numberOfDiceRolls(NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        dice.roll();
        value = dice.getValue();
        this.setWeapon(value);
        this.setArmor(value);
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
			case 5:
			{
				npcName = "Bowser";
				break;
			}
			default:
			{
				
			}
		}
		return npcName;
	}

	public static String randomNPCActionMessages(int npcValue )
	{
		String npcActionMessages = "";
		
		switch ( npcValue )
		{
		
			case 1:
			{
				npcActionMessages = "NPC attacks you with Sword";
				break;	
			}
		
			case 2:
			{
				npcActionMessages = "NPC attacks you with Knife";
				break;	
			}
			case 3:
			{
				npcActionMessages = "NPC attacks you with Machine gun";
				break;	
			}
			
			case 4:
			{
				npcActionMessages = "NPC attacks you with Two Swords";
				break;	
			}
			case 5:
			{
				npcActionMessages = "NPC attacks you with Sniper";;
				break;
			}
			case 6:
			{
				npcActionMessages = "NPC gives you spell to memorize";
				break;
			}
			default:
			{
				
			}
		}
		return npcActionMessages;
	}
	
    public String toString()
    {
    	String w="";
    	String a = "";
    	if(this.getWeapon() != null)
 		   w = "\n<br>Weapon: " + this.getWeapon().getItemName();
    	if (this.getArmor() != null)
 		   a = "\n<br>Armor: " + this.getArmor().getItemName();
        String stats = "<html>" +this.getName() +
                        " \n<br>Hit Points: " + this.getHitPoints()+
                        " \n<br>Intelligence: " + this.getIntelligence() +
                        " \n<br>Dexterity: " + this.getDexterity() + 
                        " \n<br>Strength: " + this.getStrength()+
                        w + a +
                        "\n<br>-----------------------------</html>";
        return stats;
    }
 
}
