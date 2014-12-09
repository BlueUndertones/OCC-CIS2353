package adventureGame;
import java.util.*;

import javax.print.attribute.standard.Finishings;


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
	
	public void sleep( )//demario
	{
		
		Die dice = new Die(2);
		dice.roll(); 
		int value = dice.getValue(); 
		
		if ( value == 1 )
		{
			Board.info2.setText("While you were sleeping your party was attacked!");
			//System.out.println("While you were sleeping your party was attacked!");
			//fight();//party.
		}
		else
		{
			for ( int i = 0; i < party.size(); i++ )
			{
				Player p;				
				p = (Player) party.get(i);
				p.addHitPoints();
				//System.out.println(p);
			}
		}
	}


	public void fight(Sequence<Player> sortedPlayers, Sequence<NPC> sortedNPC) //krupa
	{
		System.out.println("Fight Begins");
		boolean isDead = false;
		Player fighterPlayer, targetPlayer;
		NPC fighterNPC, targetNPC;
		int hitPoints, totalDamage;
		Weapon currentWeapon = null;
		for(int playerIndex = 0; playerIndex < sortedPlayers.size(); playerIndex++)
		{
			for(int npcIndex = 0; npcIndex < sortedNPC.size(); npcIndex++)
			{
				if(sortedPlayers.get(playerIndex).getDexterity() >= sortedNPC.get(npcIndex).getDexterity())
				{
					fighterPlayer = sortedPlayers.get(playerIndex);
					targetNPC = sortedNPC.get(npcIndex);
					hitPoints = targetNPC.getHitPoints();
					do{
						  attack(fighterPlayer,sortedNPC); 
						  totalDamage = calculateTotalDamageForNPC(fighterPlayer, targetNPC ) ;
						  hitPoints = hitPoints - totalDamage;
						  targetNPC.setHitPoints(hitPoints);
						  if( hitPoints <= 0)
						    {
						      isDead = true;
						     if(targetNPC.getWeapon() != null)
						     {
						    	 if(Board.grabWeapon() == "Yes")
						    	 currentWeapon= targetNPC.getWeapon();
						    	 fighterPlayer.setWeapon(currentWeapon);
						     }
						     sortedNPC.remove(npcIndex);
						      npcIndex++;
						    }
					  }while(!(isDead));
					
				}
				else
				{
					targetPlayer = sortedPlayers.get(playerIndex);
					fighterNPC = sortedNPC.get(npcIndex);
					hitPoints = targetPlayer.getHitPoints();
					String randomAction ="";
					
						Die dice = new Die(6) ;
						dice.roll(); 
						int value = dice.getValue(); 
						randomAction = NPC.randomNPCActionMessages(value);
						Board.info1.setText(randomAction);
						totalDamage = calculateTotalDamageForPlayer(targetPlayer,fighterNPC)  ;
						hitPoints = hitPoints-totalDamage;
						targetPlayer.setHitPoints(hitPoints);
						  
						  if(hitPoints <= 0)
						    {
						      isDead = true;
						      sortedPlayers.remove(playerIndex);
						      playerIndex++;
						    }
						  if(hitPoints > 20)
						  {
							  playerIndex++;
						  }
						  if(sortedPlayers.size()==0)
						  {
							  Board.gameOverMessage();
						  }
				}
			}
		}
	}
	
	public static void attack(Player fighterPlayer, Sequence<NPC> sortedTargetNPC)
	{
		Weapon currentWeapon = null;
		String selectedAction;
		selectedAction = Board.chooseFightActions();
		if(selectedAction == "Melee Attack")
		{
			if(fighterPlayer.getWeapon() != null)
			{
				currentWeapon = (Weapon) fighterPlayer.getWeapon();	
				Board.info1.setText("You can use weapon to fight");
			}else
			{
				Board.info1.setText("You can use your fists to fight");
			}
			
		}
		if(selectedAction == "Range Attack")
		{
			Board.info1.setText("You have selected Range Attack");
		}
		if(selectedAction == "Spell Cast")
		{
			Board.info1.setText("You have selected Spell Cast");
		}
		if(selectedAction == "Memorize Spell")
		{
			Board.info1.setText("You have selected Memorize Spell");
		}
	}

	
	public static int calculateTotalDamageForNPC(Player fighterPlayer, NPC targetNPC )
	{ 
		int totalDamage = 0;
		int weaponDamage = 0,armorDamage=0 ;
		Weapon currentWeapon = null;
		currentWeapon = (Weapon) fighterPlayer.getWeapon();
		Armor currentArmor = null;
		currentArmor = targetNPC.getArmor();
		if(currentWeapon != null)
		{
			weaponDamage = currentWeapon.getDamage();
		}
		if(currentArmor != null)
		{
			armorDamage = currentArmor.getDamage();
		}
		totalDamage = (int) (Math.ceil((targetNPC.getStrength()/3) + weaponDamage - armorDamage));
		return totalDamage;
	}

	public static int calculateTotalDamageForPlayer(Player targetPlayer, NPC fighterNPC )
	{ 
		int totalDamage = 0;
		int weaponDamage = 0,armorDamage=0 ;
		Weapon currentWeapon = null;
		currentWeapon = (Weapon) fighterNPC.getWeapon();
		Armor currentArmor = null;
		currentArmor = targetPlayer.getArmor();
		if(currentWeapon != null)
		{
			weaponDamage = currentWeapon.getDamage();
		}
		if(currentArmor != null)
		{
			armorDamage = currentArmor.getDamage();
		}
		totalDamage = (int) (Math.ceil((targetPlayer.getStrength()/3) + weaponDamage - armorDamage));
		return totalDamage;
	}

	public void search(Room room, Player player) // Heather
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
			Board.info2.setText(player.getName() + " has found " + goldFound + " gold pieces.");
			//System.out.println(player.getName() + " has found " + goldFound + " gold pieces.");
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
			Board.info2.setText("your intelligence is too low to find Gold!");
			//System.out.println("There is nothing to find here.");
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
			Board.info2.setText("While you were running your party was attacked!");
			//System.out.println("While you were running your party was attacked!");
			for ( int i = 0; i < party.size(); i++ )
			{
				Player p;				
				p = (Player) party.get(i);
				p.removeHitPoints();
				//System.out.println(p);
			}
		}
		else
		{
			Board.info2.setText("You escape before the enemy could attack!");
//			System.out.println("You escape before the enemy could attack!");
		}
	}
}
