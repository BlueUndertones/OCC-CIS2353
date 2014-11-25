package adventureGame;
import java.util.*;


public class Party
{
   static Sequence party;
   private int MIN_PARTY_SIZE = 0;
   private int MAX_PARTY_SIZE = 6;
   private static boolean isSearched = false;
   
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


	public static void fight(Player player, NPC npc) //krupa
	{
		System.out.println("Fight Begins");
		if(player.getDexterity() > npc.getDexterity())
		{
			boolean isDead = false;
           do{
        	  attack(player,npc); 
              if(player.getHitPoints() == 0)
              {
            	  isDead = true;
              }
           }while(!(isDead));
		}

	}
	
	public static void attack(Player currentPlayer, NPC target)
	{
		int chooseAction = 1;
		Weapon currentWeapon  = null;
		String useWeapon = "";
		int totalDamage;
		int armorDamage = currentPlayer.getArmor().getDamage();
		switch(chooseAction)
		{
		case 1:
			System.out.println("You have selected Melee Attack");
			System.out.println("Do you want to use your weapon or your fists?");
			if(useWeapon == "weapon")
			{
				currentWeapon = new Weapon(currentWeapon);
				currentPlayer.setWeapon(currentWeapon);
			}
			
		case 2:
			System.out.println("You have selected range Attack");
			currentWeapon = new Weapon(currentWeapon);
			currentPlayer.setWeapon(currentWeapon);
			
	    case 3:
		    System.out.println("You have selected Spell cast");
	    case 4:
	    	System.out.println("You have selected memorize spell");
	    	if(currentPlayer.getIntelligence()>=10)
	    	{
	    		System.out.println("Some spells to memorize");
	    	}
	    	else
	    		System.out.println("You can't memorize spell");
	    default:
			System.out.println("You have not selected any actions");	
		}
		int weaponDamage = currentWeapon.getDamage();
	    totalDamage = currentPlayer.getStrength()/3 + weaponDamage - armorDamage;
	}

	
	public static void search(Room room, Player player) // Heather
	{
		// check player's int
		// you can only search a room the first time you're visiting it. After
		// that, no searching.
//		if (room.isVisited() == true || isSearched == true) {
//			System.out.println("This room has already been searched.");
//			return;
//		}

		// playerGold = player.getGold();
		int playerInt = player.getIntelligence();
		Die intDie = new Die(20);
		int roomInt = intDie.roll();
		if (playerInt > roomInt) { // ? what about >= ?
			int goldFound = room.getGold();
			System.out.println(player.getName() + " has found " + goldFound + " gold pieces.");
			// gold = playerGold + goldFound;
			player.setGold(player.getGold()+ goldFound);
//			System.out.println("room gold is " + roomInt);
//			System.out.println("intele is " + player.getIntelligence());
//			System.out.println(player.getName() + "  " + player.getStrength());
			// player.addGold(gold);
		} else {
			// return nothing found..
//			System.out.println("room gold is " +roomInt);
//			System.out.println("intele is " + player.getIntelligence());
			System.out.println("There is nothing to find here.");
		}
		isSearched = true;
	}
	
	public static void hide()// nadir
	{
		//this method exists in the board class
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
}
