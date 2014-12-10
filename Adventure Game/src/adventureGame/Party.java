package adventureGame;
import java.util.*;
import javax.swing.JOptionPane;


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
			throw new IndexOutOfBoundsException( party.size() + " Must be larger than 0 and less than 6");
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
		}
		else
		{
			for ( int i = 0; i < party.size(); i++ )
			{
				Player p;				
				p = (Player) party.get(i);
				p.addHitPoints();
				Board.info2.setText("You slept well! you got more hitpoints");
				//System.out.println(p);
			}
		}
	}

	public void fight(Sequence<Player> players, Sequence<NPC> NPCs) 
	{
		int count =0;

		while (NPCs.size() > 0)
		{
			for (int i = 0; i < players.size(); i++) {

				//players to attack NPCs
				if (NPCs.size() == 1 && !(NPCs.get(0).getHitPoints() <= 0))
				{
					playerAttack(players.get(i), NPCs.get(0));
					if (NPCs.get(0).getHitPoints() <= 0)
					{	
						NPCs.remove(0);
						break;
					}
				}
				if (NPCs.size() > 1)
				{
					Random ran = new Random();
					int x = ran.nextInt(NPCs.size()-1);
					playerAttack(players.get(i), NPCs.get(x));
				}
				
				//NPCs to attack Players
				if (count > NPCs.size()-1)
					count = 0;
				if (players.size() == 1)
					NPCAttack(NPCs.get(count), players.get(0));
				else
				{
					Random ran2 = new Random();
					int y = ran2.nextInt(players.size()-1);
					NPCAttack(NPCs.get(count), players.get(y));
					count++;
				}
			}
			if(NPCs.size() == 0)
				break;
			if (players.size() == 1 && players.get(0).getHitPoints() == 0)
				break;
		}
	}
	
	public void NPCAttack(NPC currentNPC, Player target)
	{
		Die dice2 = new Die(4);
		int value2 = dice2.roll();
 
		if (currentNPC.getWeapon() != null && value2 == 2)
		{
			if (isTargetGonnaDieByWeapon(target, currentNPC))
			{
				Board.info2.setText(currentNPC.getName()+" hit"+ target.getName()+ " >>>>  " + target.getName() + " is Dead  <<<<");
				target.setHitPoints(calcDamage (target, currentNPC ));
			}
			else
			{
				Board.info2.setText(target.getName() + " got hit by: " + currentNPC.getName()+ "'s " + currentNPC.getWeapon().getItemName());
				target.setHitPoints(calcDamage (target, currentNPC ));
			}
		}
		if (currentNPC.getWeapon() != null && value2 == 1)
		{
			if (isTargetGonnaDieByWeapon(target, currentNPC))
			{
				Board.info2.setText(currentNPC.getName()+" casted a spell, "+ target.getName()+ " >>>>  " + target.getName() + " is Dead  <<<<");
				target.setHitPoints(calcDamage (target, currentNPC ));
			}
			else
			{
				if (memorizeSpell(currentNPC).equals("Yes") && canMemorizeSpell (target))
					target.setSpells(target.getSpells()+1);
				else
				{
					Board.info1.setText(target.getName() + " you're too stupid to memroze this spell");
					Board.info2.setText(target.getName() + " got hit by: " + currentNPC.getName()+ "'s " + currentNPC.getWeapon().getItemName());
					target.setHitPoints(calcDamage (target, currentNPC ));
				}
			}
		}
		if (currentNPC.getWeapon() == null && value2 == 1)
		{
			if (memorizeSpell(currentNPC).equals("Yes") && canMemorizeSpell (target))
				target.setSpells(target.getSpells()+1);
			else
			{
				Board.info1.setText(target.getName() + " you're too stupid to memroze this spell");
				Board.info2.setText(target.getName() + " got hit by: " + currentNPC.getName()+ "'s " + currentNPC.getWeapon().getItemName());
				target.setHitPoints(calcDamage (target, currentNPC ));
			}
		}
		else
		{
			Die dice = new Die(20);
			dice.roll(); 
			int value = dice.getValue();
			if (value > target.getDexterity())
			{
				if (isTargetGonnaDie(target, currentNPC))
				{
					Board.info2.setText(currentNPC.getName()+" hit"+ target.getName()+ " >>>  " + target.getName() + " is Dead  <<<");
					target.setHitPoints(calcDamage (target, currentNPC ));
				}
				else
				{
					Board.info2.setText(target.getName() + " got hit by " + currentNPC.getName());
					target.setHitPoints(calcDamage (target, currentNPC ));	
				}
			}
			else
				Board.info2.setText(currentNPC.getName() + " couldn't hit " + target.getName());
		}
	}
	
	public void playerAttack(Player currentPlayer, NPC target)
	{	
		if (!currentPlayer.isPlayerDead())
		{
			switch (chooseAction (currentPlayer)) {
			
				case 0: //meele attack
					if (currentPlayer.getWeapon() != null)
					{
						//choose what to use fists or weapon
						switch (fistOrWeapon(currentPlayer)) {
						case 0: //weapon chosen
							if (isTargetGonnaDieByWeapon(target, currentPlayer))
							{
								currentPlayer.setWeapon(target.getWeapon());
								target.setHitPoints(calcDamage (target, currentPlayer ));
							    Board.info1.setText(currentPlayer.getName() + " Killed " + target.getName() + " and took his " + target.getWeapon().getItemName());
							    	
							}
							else
							{
								target.setHitPoints(calcDamage (target, currentPlayer ));
								setHitText(currentPlayer, target);
							}
						break;	
						case 1: //fists chosen
							if (isTargetGonnaDie(target, currentPlayer))
							{
								currentPlayer.setWeapon(target.getWeapon());
								target.setHitPoints(calcDamage (target, currentPlayer ));
								Board.info1.setText(currentPlayer.getName() + " Killed " + target.getName() + " and took his " + target.getWeapon().getItemName());
							}
							else
							{
								target.setHitPoints(calcDamage (target, currentPlayer ));
								setHitText(currentPlayer, target);
							}
						break;
						}
					}
					else
					{
						Die dice = new Die(20);
						dice.roll(); 
						int value = dice.getValue();
						if (value > target.getDexterity())
						{
							if (isTargetGonnaDie(target, currentPlayer))
							{
								setKilledText(currentPlayer, target);
								target.setHitPoints(calcDamage (target, currentPlayer ));
							}
							else
							{
								setHitText(currentPlayer, target);
								target.setHitPoints(calcDamage (target, currentPlayer ));
							}
						}
						else
							setMissedText(currentPlayer, target);
					}
					break;
					
				case 1: //range attack
					if (currentPlayer.getWeapon() != null)
					{
						Die dice = new Die(15);
						dice.roll(); 
						int value = dice.getValue();
						if (value > target.getDexterity())
						{
							if (isTargetGonnaDieByWeapon(target, currentPlayer))
							{
								currentPlayer.setWeapon(target.getWeapon());
								target.setHitPoints(calcDamage (target, currentPlayer ));
								Board.info1.setText(currentPlayer.getName() + " Killed " + target.getName() + " and took his " + target.getWeapon().getItemName());	
							}
							else
							{
								target.setHitPoints(calcDamage (target, currentPlayer ));
								setHitText(currentPlayer, target);
							}
						}
						else
							setMissedText(currentPlayer, target);
					}
					else
					{
						Board.info1.setText(currentPlayer.getName()+" don't have a weapon, choose again");
						playerAttack(currentPlayer, target);
					}
					
					break;
					
				case 2: //cast spell
					if (currentPlayer.getSpells() != 0)
					{
						if (target.getWeapon() != null) //is dying and he has weapon
						{
							if (isTargetGonnaDie(target, currentPlayer))
							{
								currentPlayer.setWeapon(target.getWeapon());
								target.setHitPoints(calcDamage (target, currentPlayer ));
								Board.info1.setText(currentPlayer.getName()+ " Killed " + target.getName() + " and took his " + target.getWeapon().getItemName());
								currentPlayer.setSpells(currentPlayer.getSpells()-1);
							}
							else
							{
								Board.info1.setText(currentPlayer.getName()+ " casted a spell on " + target.getName());
								target.setHitPoints(calcDamage (target, currentPlayer ));
								currentPlayer.setSpells(currentPlayer.getSpells()-1);
							}
						}
						else
						{
							if (isTargetGonnaDie(target, currentPlayer))
							{
								Board.info1.setText(currentPlayer.getName()+ " casted a spell and Killed " + target.getName());
								target.setHitPoints(calcDamage (target, currentPlayer ));
								currentPlayer.setSpells(currentPlayer.getSpells()-1);
							}
							else
							{
								Board.info1.setText(currentPlayer.getName()+" casted a spell on " + target.getName());
								target.setHitPoints(calcDamage (target, currentPlayer ));	
								currentPlayer.setSpells(currentPlayer.getSpells()-1);
							}
						}
					}
					else
					{
						Board.info1.setText(currentPlayer.getName()+" you didn't memorize any spells Choose again!");
						playerAttack(currentPlayer, target);
					}
					break;
					
				case 3: //hide
					hide(currentPlayer);
					break;
					
				case -1:
					System.exit(0);
				default:
					Board.info1.setText("You have not selected any actions");
			}
		}
	}
	public void hide(Player currentPlayer)
	{
		Player playerOnTheLead = null;
		currentPlayer.isHidden = true;
		int lastPlayer = party.size() -1;
		if (party.size() > 1)
		{
			//look for and get the next not hidden player tp take the hit and be currentOnlead
			for (int i = 0; i < party.size(); i++)
			{	
				if (!((Player)party.get(i)).isHidden && ((Player) party.get(i)).getHitPoints() != 0)
				{
					playerOnTheLead = (Player) party.get(i);
					playerOnTheLead.setTileY(Board.currentRoom.getY());
					playerOnTheLead.setTileX(Board.currentRoom.getX());
					playerOnTheLead.removeHitPoints();
					Board.info1.setText(currentPlayer.getName() +" hide, " + playerOnTheLead.getName()+" you just got hit");
					break;
				}
				//if no next player was unhidden then get all the player to get hit
				if ( ((Player)party.get(lastPlayer)).isHidden == true)
				{
					for (int x = 0; x < party.size(); x++)
					{
						((Player) party.get(i)).removeHitPoints();
						Board.info2.setText("Everybody got hit, because all are trying to hide");
					}
				}
			}
		}
		else
		{
			playerOnTheLead = (Player) party.get(0);
			Board.info1.setText("None to hide behind, " + playerOnTheLead.getName()+", you just got hit");
			playerOnTheLead.removeHitPoints();
		}		
	}
	public int calcDamage (Player target, Player currentPlayer )
	{	
		int armor = 0;
		int weaponDamage = 0;
		
		if (target.getArmor() != null)
			armor = target.getArmor().getDamage();
		if (currentPlayer.getWeapon() != null)
			weaponDamage = currentPlayer.getWeapon().getDamage();
		
		int deduction = (Math.round( target.getStrength() /3)) + weaponDamage;
		if (armor >= deduction)
			return target.getHitPoints()-1;
		if (deduction - armor < 0 )
			return 0;
		if (target.getHitPoints()- (deduction - armor) < 0)
			return 0;
		else 
			return target.getHitPoints()- (deduction - armor);
	}
	public boolean canMemorizeSpell(Player currentPlayer)
	{
		if (currentPlayer.getIntelligence() > 5)
			return true;
		else 
			return false;
	}
	public boolean isTargetGonnaDie (Player target, Player currentPlayer)
	{
		if (calcDamage(target, currentPlayer) <= 0)
			return true;
		else
			return false;
	}
	public boolean isTargetGonnaDieByWeapon (Player target, Player currentPlayer)
	{
		if (calcDamage(target, currentPlayer) <= 0)
			return true;
		else
			return false;
	}
	public void setHitText(Player currentPlayer, NPC target)
	{
		Board.info1.setText(currentPlayer.getName()+" hit "  + target.getName());
	}
	public void setKilledText(Player currentPlayer, NPC target)
	{
		Board.info1.setText(currentPlayer.getName()+" Killed "  + target.getName());
	}
	public void setMissedText(Player currentPlayer, NPC target)
	{
		Board.info1.setText(currentPlayer.getName()+" missed the target "  + target.getName());
	}
	public int fistOrWeapon (Player currentPlayer)
	{
		String[] choices = {"Weapon", "Fists"};
	    int actionChosen = JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , currentPlayer.getName()+", Do you want to use your weapon or your fists?"  // Message
	                             , "Choose Weapon or fists"            // Title in titlebar
	                             , JOptionPane.YES_NO_OPTION  // Option type
	                             , JOptionPane.PLAIN_MESSAGE  // messageType
	                             , null                       // Icon (none)
	                             , choices                    // Button text as above.
	                             , "Default"    // Default button's label
	                           );
	    
	    return actionChosen;
	}
	public int chooseAction (Player currentPlayer)
	{
		String[] choices = {"Melee", "Range Attack", "Cast Spell", "Hide"};
	    int actionChosen = JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , currentPlayer.getName()+" it's your turn, Choose how do you want to attack"  // Message
	                             , "Choose Action"            // Title in titlebar
	                             , JOptionPane.YES_NO_OPTION  // Option type
	                             , JOptionPane.PLAIN_MESSAGE  // messageType
	                             , null                       // Icon (none)
	                             , choices                    // Button text as above.
	                             , "Default"    // Default button's label
	                           );
	    
	    return actionChosen;
	}
	public static String memorizeSpell (NPC npc)
	{
		String[] selectedChoices = {"Yes","No"};
		String resString = "";
	    int choices = JOptionPane.showOptionDialog(
	                               null                       
	                             , npc.getName() + " casted a spell, \n do you want to memorize it?"  
	                             , "Choose Action"            
	                             , JOptionPane.YES_NO_OPTION  
	                             , JOptionPane.PLAIN_MESSAGE  
	                             , null                       
	                             , selectedChoices                 
	                             , "Default"    
	                           );
	    if(choices == 0)
	    {
	    	resString = "Yes";
	    }
	    else
	    	resString = "No";
	    
	    return resString;
	}
	public void search(Room room, Player player) // Heather
	{
		int playerInt = player.getIntelligence();
		Die intDie = new Die(20);
		int roomInt = intDie.roll();
		if (playerInt > roomInt) { // ? what about >= ?
			int goldFound = room.getGold();
			Board.info2.setText(player.getName() + " has found " + goldFound + " gold pieces.");
			player.setGold(player.getGold()+ goldFound);
		} else {
			Board.info2.setText("your intelligence is too low to find Gold!");
		}
		isSearched = true;
	}
	
	public void run()//demario
	{
		Die dice = new Die(2);
		dice.roll(); 
		int value = dice.getValue(); 
		
		if ( value == 1 )
		{
			Board.info2.setText("While you were running your party was attacked!");
			for ( int i = 0; i < party.size(); i++ )
			{
				Player p;				
				p = (Player) party.get(i);
				p.removeHitPoints();
			}
		}
		else
		{
			Board.info2.setText("You escaped before the enemy could attack!");
		}
	}
}
