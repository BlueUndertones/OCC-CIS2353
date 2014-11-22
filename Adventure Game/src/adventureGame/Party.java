package adventureGame;
import java.util.*;


public class Party
{
   static Sequence party;
   private int MIN_PARTY_SIZE = 0;
   private int MAX_PARTY_SIZE = 6;
   
	public Party(  Sequence party  ) //demario
	{
		if (party.size() < MIN_PARTY_SIZE|| party.size() > MAX_PARTY_SIZE  )
		{
			throw new IndexOutOfBoundsException( party.size() + " Must be "
												+ "larger than 0 and less than 6");
		}
		else 
		{
			this.party = party;
		}
	}
	
	public static void sleep( )//demario
	{
		
		Die dice = new Die(2);
		dice.roll(); 
		int value = dice.getValue(); 
		
		if ( value == 1 )
		{
			System.out.println("While you were sleeping your party was attacked!");
			fight();//party.
		}
		else
		{
			for ( int i = 0; i < party.size(); i++ )
			{
				Player p;				
				p = (Player) party.get(i);
				p.addHitPoints();
				System.out.println(p);
			}
		}
	}


	public static void fight() //krupa
	{
		System.out.println("Fight Begins");
	}
	
	public static void search() // Heather
	{
		 
	}
	
	public static void hide()// nadir
	{
		
	}
	
	public static void run()//demario
	{
		Die dice = new Die(2);
		dice.roll(); 
		int value = dice.getValue(); 
		
		if ( value == 1 )
		{
			System.out.println("While you were running your party was attacked!");
			for ( int i = 0; i < party.size(); i++ )
			{
				Player p;				
				p = (Player) party.get(i);
				p.removeHitPoints();
				System.out.println(p);
			}
		}
		else
		{
			System.out.println("You escape before the enemy could attack!");
		}
	}
	
	public String getNpcName(int npcValue )
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
}