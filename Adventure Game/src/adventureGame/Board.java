package adventureGame;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	
	private final int TILE_WIDTH = 60;
	private final int TILE_HEIGHT = 60;
	private Timer timer;
	private Map map;
	private Player players;
	private NPC npc;
	Random ran = new Random ();
	ArrayList <Position> roomsVisited = new <Position> ArrayList ();
	ArrayList <Position> roomsInvalid = new <Position> ArrayList ();
	ArrayList<Player> myPlayers = new <Player> ArrayList();
	//ArrayList <Integer> num = new <Integer> ArrayList () ;
	//ArrayList <Position> roomsHasGold = new <Position> ArrayList ();
	Room currentRoom;
	ArrayList <NPC> currentRoomNPCs =  new <NPC> ArrayList();
	
	public Board () {
		map = new Map();
		choosePlayers();
		players = myPlayers.get(0);
		npc = new NPC("Witch");
		//saveRandomRoomNumbers ();  //used to generate gold
		addKeyListener(new Al());
		setFocusable(true);
		timer = new Timer (25, this);
		timer.start();
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
		if (myPlayers.size() > 0 )
		{
			for(int i =0; i < myPlayers.size(); i ++)
			{
				JLabel info = new JLabel();
				info.setText(myPlayers.get(i).toString());
				infoPanel.add(info);
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
				JLabel info = new JLabel();
				info.setText(currentRoomNPCs.get(i).toString());
				NpcInfo.add(info);
			}
		}
		repaint();
		return NpcInfo;
	}
	public class Al extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_UP){
            	if(map.grid[players.getTileY()-1][players.getTitleX()] == 1){
            		if(!checkIfVisited ((players.getTileY()-1), players.getTitleX() ))
            		{
            			currentRoom = new Room ((players.getTileY()-1), players.getTitleX() );
                		currentRoomNPCs = currentRoom.getNPCs();
            			roomsVisited.add(new Position ((players.getTileY()-1), players.getTitleX())); 		
                		players.move(0, -1);
                		if (currentRoom.getNPCs().size() > 0)
                			doActionWithNPC(chooseActionWithNPC ());
                		else
                			doAction(chooseAction ());
            		}
            		else
            			players.move(0, -1);
            			
            	}
            	else {
            		roomsInvalid.add(new Position ((players.getTileY()-1), players.getTitleX()));
            		thatsWallOutput();
           
            	}	
			}
            if(keyCode == KeyEvent.VK_DOWN){
            	if(map.grid[players.getTileY()+1][players.getTitleX()] == 1){
            		if(!checkIfVisited ((players.getTileY()+1), players.getTitleX() ))
            		{
	            		currentRoom = new Room ((players.getTileY()+1), players.getTitleX() );
	            		currentRoomNPCs = currentRoom.getNPCs();
	            		roomsVisited.add(new Position ((players.getTileY()+1), players.getTitleX()));
	            		players.move(0, 1);
	            		if (currentRoom.getNPCs().size() > 0)
	            			doActionWithNPC(chooseActionWithNPC ());
	            		else
	            			doAction(chooseAction ());
            		}
            		else
            			players.move(0, 1);
            	}
            	else {
            		roomsInvalid.add(new Position ((players.getTileY()+1), players.getTitleX()));
            		thatsWallOutput();
            	}
            }
            if(keyCode == KeyEvent.VK_LEFT){
            	if(map.grid[players.getTileY()][players.getTitleX()-1] == 1){
            		if(!checkIfVisited (players.getTileY(), (players.getTitleX()-1) ))
            		{
	            		currentRoom = new Room (players.getTileY(), (players.getTitleX()-1) );
	            		currentRoomNPCs = currentRoom.getNPCs();
	            		roomsVisited.add(new Position (players.getTileY(), (players.getTitleX()-1)));
	            		players.move(-1, 0);
	            		if (currentRoom.getNPCs().size() > 0)
	            			doActionWithNPC(chooseActionWithNPC ());
	            		else
	            			doAction(chooseAction ());
            		}
            		else
            			players.move(-1, 0);
            	}
            	else {
            		roomsInvalid.add(new Position (players.getTileY(), players.getTitleX()-1));
            		thatsWallOutput();
            	}
            }
            if(keyCode == KeyEvent.VK_RIGHT){
            	if(map.grid[players.getTileY()][players.getTitleX()+1] == 1){
            		if(!checkIfVisited (players.getTileY(), (players.getTitleX()+1) ))
            		{
	            		currentRoom = new Room (players.getTileY(), (players.getTitleX()+1) );
	            		currentRoomNPCs = currentRoom.getNPCs();
	            		roomsVisited.add(new Position (players.getTileY(), (players.getTitleX()+1)));
	            		players.move(1, 0);
	            		if (currentRoom.getNPCs().size() > 0)
	            			doActionWithNPC(chooseActionWithNPC ());
	            		else
	            			doAction(chooseAction ());
            		}
            		else
            			players.move(1, 0);
            	}
            	else {
            		roomsInvalid.add(new Position (players.getTileY(), players.getTitleX()+1));
            		thatsWallOutput();
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
//			g.setColor(Color.black);
//			g.fillRect(y * TILE_WIDTH +1, x * TILE_HEIGHT +1,  TILE_WIDTH, TILE_HEIGHT);
			g.drawImage(drawImage("locked.png"), y * TILE_WIDTH +1, x * TILE_HEIGHT +1, TILE_WIDTH, TILE_HEIGHT, null);
		}
		
		//draw gold on the maze
		/*for(int i =0; i < 10; i++)
		{
	        g.drawImage(drawImage("gold.png"), roomsHasGold.get(i).getColumn() * TILE_WIDTH, roomsHasGold.get(i).getRow() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null);
		}*/
		
		//draw mario image on the board
        g.drawImage(drawImage("players.png"), players.getTitleX() * TILE_WIDTH, players.getTileY() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null);  
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
	
	public void thatsWallOutput ()
	{
		JOptionPane.showMessageDialog (null, "Room not available --WALL", "WALL", JOptionPane.INFORMATION_MESSAGE);
	}
	/*
	public void roomHasGold()
	{
		JOptionPane.showMessageDialog (null, "Room Has Gold", "WALL", JOptionPane.INFORMATION_MESSAGE);
		p.setGold(1);
		//gold = p.getGold();
		info.setText(p.toString(name, hitPoints, intelligence, dexterity, strength, p.getGold()));
		
	}
	*/
	/*
	public boolean checkForGold (int y, int x)
	{
		Position pos = new Position (y, x);
		for (int i = 0; i < roomsHasGold.size(); i++)
			if (roomsHasGold.get(i).equals(pos))
				return true;
		return false;
	}
	*/
	public int chooseActionWithNPC ()
	{
		String[] choices = {"Fight", "Run", "Hide"};
	    int actionChosen = JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , currentRoom.getNPCs().size()+ " NPC in This Room, \n What Do you want to do?"  // Message
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
		
	    int res = JOptionPane.showConfirmDialog(null, array, "Select", JOptionPane.OK_CANCEL_OPTION);

		if (mario.isSelected()) { 
			myPlayers.add(new Player ("Mario"));
		}
		
		if (link.isSelected()) {
			myPlayers.add(new Player ("Link")); 
		}
		
		if (strife.isSelected()) {
			myPlayers.add(new Player ("Strife")); 
		}
		if (kratos.isSelected()) { 
			myPlayers.add(new Player ("Kratos")); 
		}
		
		if (sonic.isSelected()) {
			myPlayers.add(new Player ("Sonic")); 
		}
		
		if (arthus.isSelected()) {
			myPlayers.add(new Player ("Arthus"));
		}
		if (res == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		if (myPlayers.size() == 0)
			choosePlayers();
	}
	
	public void doActionWithNPC (int actionChosen)
	{
		switch (actionChosen) {
        case 0: 
            Party.fight();
            break;
        case 1:
        	Party.run();
            break;
        case 2:
        	Party.hide();
            break;
        //case -1:
            //System.exit(0); 
        default:
            JOptionPane.showMessageDialog(null, "Unexpected response " + actionChosen);
		}
	}
	
	public void doAction (int actionChosen)
	{
		switch (actionChosen) {
        case 0:
            Party.sleep();
            break;
        case 1:
        	Party.search();
        	break;
        case 2:
        	break;
        //case -1:
            //System.exit(0); 
        default:
            JOptionPane.showMessageDialog(null, "Unexpected response " + actionChosen);
		}
	}
}
