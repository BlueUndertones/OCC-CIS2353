package adventureGame;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	
	private final int TILE_WIDTH = 50;
	private final int TILE_HEIGHT = 50;
	private Timer timer;
	private Map map;
	private Player playerOnTheLead;
	private NPC npc;
	Random ran = new Random ();
	Sequence <Position> roomsVisited = new <Position> Sequence ();
	Sequence <Position> roomsInvalid = new <Position> Sequence ();
	Sequence<Player> myPlayersArray = new <Player> Sequence();
	Sequence<Player> sortedPlayers = new <Player> Sequence();
	Party myPlayersParty = new Party(sortedPlayers);
	//	ArrayList<Player> myPlayersArray = new <Player> ArrayList();
	//ArrayList <Integer> num = new <Integer> ArrayList () ;
	//ArrayList <Position> roomsHasGold = new <Position> ArrayList ();
	static Room currentRoom;
	int fontSize = 0;
	Sequence <NPC> currentRoomNPCs =  new <NPC> Sequence();
	static JTextArea info2 = new JTextArea();
	static JTextArea info1 = new JTextArea();
	
	public Board () {
		map = new Map();
		choosePlayers();		
		sortMyPlayers(myPlayersArray);
		playerOnTheLead = sortedPlayers.get(0);
		currentRoom = new Room ((playerOnTheLead.getTileY()), playerOnTheLead.getTileX() );
		npc = new NPC("Witch");
		//saveRandomRoomNumbers ();  //used to generate gold
		addKeyListener(new Al());
		setFocusable(true);
		timer = new Timer (25, this);
		timer.start();
//		Collections.sort(myPlayersArray, new Comparator<Player>() {
//			@Override
//			public int compare(Player o1, Player o2) {
//				final int dex1 = o1.getDexterity();
//	            final int dex2 = o2.getDexterity();
//	            return dex1 < dex2 ? -1 : dex1 > dex2 ? 1 : 0;
//			}
//	    }
//		);
	}
	
	/*//save random room numbers to draw the random gold on the maze
	public void saveRandomRoomNumbers ()
	{
		//loop through walk through rooms and get random rooms
		for (int i =0; i < 10; i++)
			num.add (ran.nextInt(map.rooms.size()));
		//save above random room numbers to have gold in an array list
		for (int i =0; i < num.size(); i++)
			roomsHasGold.add(new Position (map.rooms.get(num.get(i)).getRow(), map.rooms.get(num.get(i)).getColumn()));
	}*/
	public JPanel buildPlayerInfo ()
	{
		JPanel infoPanel = new JPanel(new GridLayout(0,1));
		if (sortedPlayers.size() > 0 )
		{
			for(int i =0; i < sortedPlayers.size(); i ++)
			{
				if (!sortedPlayers.get(i).isPlayerDead())
				{
					JLabel info = new JLabel();
					info.setText(sortedPlayers.get(i).toString());
					info.setFont (new Font ("Serif", Font.BOLD, 11));
					infoPanel.add(info);
				}
				else
				{
					String playerName = sortedPlayers.get(i).getName();
					sortedPlayers.remove(i);
					JLabel info = new JLabel();
					info.setText( playerName + " has Died");
					infoPanel.add(info);
					if (sortedPlayers.size() == 0)
						closeGame(gameOverMessage());
				}
			}
		}
		repaint();
		return infoPanel;
	}
	public JPanel buildNpcInfo ()
	{
		JPanel NpcInfo = new JPanel(new GridLayout(0,1));
		if (currentRoomNPCs.size() > 0)
		{
			for(int i =0; i < currentRoomNPCs.size(); i ++)
			{
				if (!currentRoomNPCs.get(i).isPlayerDead())
				{
					JLabel info = new JLabel();
					info.setText(currentRoomNPCs.get(i).toString());
					info.setFont (new Font ("Serif", Font.BOLD, 9));
					NpcInfo.add(info);
				}
				else
				{
					String npc = currentRoomNPCs.get(i).getName();
					currentRoomNPCs.remove(i);
					JLabel info = new JLabel();
					info.setText( npc + " has Died");
					NpcInfo.add(info);

				}
			}
		}
		repaint();
		return NpcInfo;
	}
	public JPanel buildRoomInfo ()
	{
		JPanel NpcInfo = new JPanel(new GridLayout(0,1));
		
		JTextArea info = new JTextArea();
		info.setText(currentRoom.toString());
		//info.setSize(300, 100);
		info.setLineWrap(true);
		//info.setColumns(10);
		//info.setRows(1);
		info.setWrapStyleWord(true);
		NpcInfo.add(info);
		return NpcInfo;
	}
	public JPanel partyPanel ()
	{
		JPanel NpcInfo = new JPanel();
		info2.setFont (new Font ("Serif", Font.BOLD, 14));
		NpcInfo.add(info2);
		repaint();
		return NpcInfo;
	}
	public JPanel fightPanel ()
	{
		JPanel NpcInfo = new JPanel();
		info1.setFont (new Font ("Serif", Font.BOLD, 16));
		NpcInfo.add(info1);
		repaint();
		return NpcInfo;
	}
	public class Al extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_UP){
            	if(map.grid[playerOnTheLead.getTileY()-1][playerOnTheLead.getTileX()] == 1){
            		if(!checkIfVisited ((playerOnTheLead.getTileY()-1), playerOnTheLead.getTileX() ))
            		{
            			currentRoom = new Room ((playerOnTheLead.getTileY()-1), playerOnTheLead.getTileX() );
                		currentRoomNPCs = currentRoom.getNPCs();
            			roomsVisited.append(new Position ((playerOnTheLead.getTileY()-1), playerOnTheLead.getTileX())); 		
                		playerOnTheLead.move(0, -1);
                		if (currentRoom.getNPCs().size() > 0)
                			doActionWithNPC(chooseActionWithNPC ());
                		else
                			doAction(chooseAction ());
            		}
            		else
            			playerOnTheLead.move(0, -1);
            			
            	}
            	else {
            		roomsInvalid.append(new Position ((playerOnTheLead.getTileY()-1), playerOnTheLead.getTileX()));
            	}	
			}
            if(keyCode == KeyEvent.VK_DOWN){
            	if(map.grid[playerOnTheLead.getTileY()+1][playerOnTheLead.getTileX()] == 1){
            		if(!checkIfVisited ((playerOnTheLead.getTileY()+1), playerOnTheLead.getTileX() ))
            		{
	            		currentRoom = new Room ((playerOnTheLead.getTileY()+1), playerOnTheLead.getTileX() );
	            		currentRoomNPCs = currentRoom.getNPCs();
	            		roomsVisited.append(new Position ((playerOnTheLead.getTileY()+1), playerOnTheLead.getTileX()));
	            		playerOnTheLead.move(0, 1);
	            		if (currentRoom.getNPCs().size() > 0)
	            			doActionWithNPC(chooseActionWithNPC ());
	            		else
	            			doAction(chooseAction ());
            		}
            		else
            			playerOnTheLead.move(0, 1);
            	}
            	else {
            		roomsInvalid.append(new Position ((playerOnTheLead.getTileY()+1), playerOnTheLead.getTileX()));
            	}
            }
            if(keyCode == KeyEvent.VK_LEFT){
            	if(map.grid[playerOnTheLead.getTileY()][playerOnTheLead.getTileX()-1] == 1){
            		if(!checkIfVisited (playerOnTheLead.getTileY(), (playerOnTheLead.getTileX()-1) ))
            		{
	            		currentRoom = new Room (playerOnTheLead.getTileY(), (playerOnTheLead.getTileX()-1) );
	            		currentRoomNPCs = currentRoom.getNPCs();
	            		roomsVisited.append(new Position (playerOnTheLead.getTileY(), (playerOnTheLead.getTileX()-1)));
	            		playerOnTheLead.move(-1, 0);
	            		if (currentRoom.getNPCs().size() > 0)
	            			doActionWithNPC(chooseActionWithNPC ());
	            		else
	            			doAction(chooseAction ());
            		}
            		else
            			playerOnTheLead.move(-1, 0);
            	}
            	else {
            		roomsInvalid.append(new Position (playerOnTheLead.getTileY(), playerOnTheLead.getTileX()-1));
            	}
            }
            if(keyCode == KeyEvent.VK_RIGHT){
            	if(map.grid[playerOnTheLead.getTileY()][playerOnTheLead.getTileX()+1] == 1){
            		if(!checkIfVisited (playerOnTheLead.getTileY(), (playerOnTheLead.getTileX()+1) ))
            		{
	            		currentRoom = new Room (playerOnTheLead.getTileY(), (playerOnTheLead.getTileX()+1) );
	            		currentRoomNPCs = currentRoom.getNPCs();
	            		roomsVisited.append(new Position (playerOnTheLead.getTileY(), (playerOnTheLead.getTileX()+1)));
	            		playerOnTheLead.move(1, 0);
	            		if (currentRoom.getNPCs().size() > 0)
	            			doActionWithNPC(chooseActionWithNPC ());
	            		else
	            			doAction(chooseAction ());
            		}
            		else
            			playerOnTheLead.move(1, 0);
            	}
            	else {
            		roomsInvalid.append(new Position (playerOnTheLead.getTileY(), playerOnTheLead.getTileX()+1));
            	}
            }
		}
	}
	public boolean checkIfVisited (int y, int x)
	{
		Position pos = new Position (y, x);
		for (int i = 0; i < roomsVisited.size(); i++)
			if (roomsVisited.get(i).equals(pos))
				return true;
		return false;
	}
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void paint (Graphics g)
	{
		super.paint(g);
		for (int x = 0; x < map.grid.length; x++) 
        {
             for (int y = 0; y < map.grid [0].length; y++)
             {
				//g.setColor(Color.white);
				//g.fillRect(y * TILE_WIDTH, x * TILE_HEIGHT,  TILE_WIDTH, TILE_HEIGHT);
				//g.setColor(Color.gray);
				//g.fillRect(y * TILE_WIDTH+1, x * TILE_HEIGHT +1,  TILE_WIDTH, TILE_HEIGHT);
            	 g.drawImage(drawImage("tile.jpg"), y * TILE_WIDTH+1, x * TILE_HEIGHT +1, TILE_WIDTH, TILE_HEIGHT, null);
            	 if (map.grid[x][y] == 9)
            		 g.drawImage(drawImage("end.png"), y * TILE_WIDTH+1, x * TILE_HEIGHT +1, TILE_WIDTH, TILE_HEIGHT, null);
			}
		}
		for (int index =0; index < roomsVisited.size(); index++)
		{
			int x = roomsVisited.get(index).getRow();
			int y = roomsVisited.get(index).getColumn();
			g.setColor(Color.white);
			g.fillRect(y * TILE_WIDTH +1, x * TILE_HEIGHT +1,  TILE_WIDTH, TILE_HEIGHT);
		}
		for (int index =0; index < roomsInvalid.size(); index++)
		{
			int x = roomsInvalid.get(index).getRow();
			int y = roomsInvalid.get(index).getColumn();
			g.drawImage(drawImage("locked.png"), y * TILE_WIDTH +1, x * TILE_HEIGHT +1, TILE_WIDTH, TILE_HEIGHT, null);
		}
		
        g.drawImage(drawImage("players.png"), playerOnTheLead.getTileX() * TILE_WIDTH, playerOnTheLead.getTileY() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null);  
	}

	public Image drawImage (String imagePath)
	{
		Image img = null;
		try {
			img = ImageIO.read(this.getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public int chooseActionWithNPC ()
	{
		String[] choices = {"Fight", "Run"};
	    int actionChosen = JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , currentRoom.getNPCs().size()+ " NPC(s) in This Room, \n What Do you want to do?"  // Message
	                             , "Choose Action"            // Title in titlebar
	                             , JOptionPane.YES_NO_OPTION  // Option type
	                             , JOptionPane.PLAIN_MESSAGE  // messageType
	                             , null                       // Icon (none)
	                             , choices                    // Button text as above.
	                             , "Default"    // Default button's label
	                           );
	    
	    return actionChosen;
	}
	
	public int chooseAction ()
	{
		String[] choices = {"Sleep", "Search", "Move On"};
	    int actionChosen = JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , "No NPCs in this Room, \n What Do you want to do?"  // Message
	                             , "Choose Action"            // Title in titlebar
	                             , JOptionPane.YES_NO_OPTION  // Option type
	                             , JOptionPane.PLAIN_MESSAGE  // messageType
	                             , null                       // Icon (none)
	                             , choices                    // Button text as above.
	                             , "Default"    // Default button's label
	                           );
	    
	    return actionChosen;
	}
	
	public void doActionWithNPC (int actionChosen)
	{
		boolean f = false;
		switch (actionChosen) {
        case 0: 
        	myPlayersParty.fight(sortedPlayers ,currentRoom.getNPCs());
            break;
        case 1:
        	myPlayersParty.run();
        	roomsVisited.remove(roomsVisited.size()-1);
            break;
//        case 2:
//        	hide();
//            break;
//        case 3:
//        	myPlayersParty.sleep();
//            break;
        case -1:
            System.exit(0); 
        default:
            JOptionPane.showMessageDialog(null, "Unexpected response " + actionChosen);
		}
	}
	
	public void doAction (int actionChosen)
	{
		switch (actionChosen) {
        case 0:
        	myPlayersParty.sleep();
            break;
        case 1:
        	myPlayersParty.search(new Room(playerOnTheLead.getTileX(), playerOnTheLead.getTileY()), playerOnTheLead );
        	break;
        case 2:
        	break;
        //case -1:
            //System.exit(0); 
        default:
            JOptionPane.showMessageDialog(null, "Unexpected response " + actionChosen);
		}
	}
	
	public void choosePlayers()
	{
		JCheckBox mario = new JCheckBox("Mario");
		JCheckBox link = new JCheckBox("Link");
		JCheckBox strife = new JCheckBox("Cloud Strife");
		JCheckBox kratos = new JCheckBox("Kratos");
		JCheckBox sonic = new JCheckBox("Sonic");
		JCheckBox arthus = new JCheckBox("Arthus");
		
		mario.setSelected(true);
	  
		Object[] array = {
		        new JLabel("Select at least one Player:"),
		        mario,
		        link,
		        strife,
		        kratos,
		        sonic,
		        arthus   
		};
		
	    int res = JOptionPane.showConfirmDialog(null, array, "Select Your Players", JOptionPane.OK_CANCEL_OPTION);

		if (mario.isSelected()) { 
			myPlayersArray.append(new Player ("Mario"));
		}
		
		if (link.isSelected()) {
			myPlayersArray.append(new Player ("Link")); 
		}
		
		if (strife.isSelected()) {
			myPlayersArray.append(new Player ("Strife")); 
		}
		if (kratos.isSelected()) { 
			myPlayersArray.append(new Player ("Kratos")); 
		}
		
		if (sonic.isSelected()) {
			myPlayersArray.append(new Player ("Sonic")); 
		}
		
		if (arthus.isSelected()) {
			myPlayersArray.append(new Player ("Arthus"));
		}
		if (res == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		if (myPlayersArray.size() == 0)
			choosePlayers();
	}
	
	public static String chooseFightActions()
	{
		String action = " ";
		
		JRadioButton  melee = new JRadioButton ("Melee Attack");
		JRadioButton  range = new JRadioButton ("Range Attack");
		JRadioButton  spellCast = new JRadioButton ("Spell Cast");
		JRadioButton  memoriseSpell = new JRadioButton ("Memorize Spell");
	  
		ButtonGroup group = new ButtonGroup();
	    group.add(melee);
	    group.add(range);
	    group.add(spellCast);
	    group.add(memoriseSpell);
		
		Object[] array = {
		        new JLabel("Select any action:"),
		        melee,
		        range,
		        spellCast,
		        memoriseSpell,  
		};
		
	    int res = JOptionPane.showConfirmDialog(null, array, "Select Your Action", JOptionPane.OK_CANCEL_OPTION);

		if (melee.isSelected()) { 
			action = "Melee Attack";
		}
		
		if (range.isSelected()) {
			action = "Range Attack"; 
		}
		
		if (spellCast.isSelected()) {
			action = "Spell Cast"; 
		}
		if (memoriseSpell.isSelected()) { 
			action = "Memorize Spell"; 
		}
		if (res == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		
		return action;
	}
	
	public static String grabWeapon (NPC deadPlayer)
	{
		String[] selectedChoices = {"Yes","No"};
		String resString = "";
	    int choices = JOptionPane.showOptionDialog(
	                               null                       
	                             , deadPlayer.getName() + " was killed and dropped the weapon, \n do you want to grab it?"  
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

	
	public static int gameOverMessage ()
	{
		String[] choices = {"OK"};
	    int actionChosen = JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , "Game Over!! \n all your Players are dead \n you're too dumb for this game :("  // Message
	                             , "Game Over"            // Title in titlebar
	                             , JOptionPane.YES_NO_OPTION  // Option type
	                             , JOptionPane.PLAIN_MESSAGE  // messageType
	                             , null                       // Icon (none)
	                             , choices                    // Button text as above.
	                             , "Default"    // Default button's label
	                           );
	    
	    return actionChosen;
	}
	
	public void closeGame (int actionChosen)
	{
		switch (actionChosen) {
        case 0: 
            System.exit(0); 
            break;
        case -1:
            System.exit(0); 
        default:
            JOptionPane.showMessageDialog(null, "Unexpected response " + actionChosen);
		}
	}
	public void printSorted (Sequence<Player>sortedPlayers)
	{
		for (int i = 0; i < sortedPlayers.size(); i++)
			System.out.println(sortedPlayers.get(i));
	}
	public void sortMyPlayers(Sequence<Player> myPlayers)
	{
		
		Sequence<Player> ps =  myPlayers;
		while (ps.size() > 0)
		{
			int i = 0;
		    int index = findHighestDext(ps);
		    sortedPlayers.append(ps.get(index));
		    ps.remove(index);
		    i++;
		}
	}
	public int findHighestDext(Sequence<Player> myPlayers)
	{
		Player highestDex = null;
		int index = 0;
		for (int i =0; i < myPlayers.size(); i++)
		{
			if (highestDex == null || myPlayers.get(i).getDexterity() > highestDex.getDexterity())
			{
				highestDex = myPlayers.get(i);
				index = i;
			}
		}
		return index;
	}
}
