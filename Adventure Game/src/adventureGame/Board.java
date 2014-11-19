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
	private Player p;
	Random ran = new Random ();
	ArrayList <Integer> num = new <Integer> ArrayList () ;
	ArrayList <Position> roomsVisited = new <Position> ArrayList ();
	ArrayList <Position> roomsInvalid = new <Position> ArrayList ();
	//ArrayList <Position> roomsHasGold = new <Position> ArrayList ();
	String name;
	int hitPoints;
	int intelligence;
	int dexterity;
	int strength;
	int gold;
	JLabel info = new JLabel();
	
	public Board () {
		map = new Map();
		p = new Player("Mario");
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
		JPanel infoPanel = new JPanel();
		info.setText(p.toString());
		infoPanel.add(info);
		repaint();
		return infoPanel;
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
				g.setColor(Color.white);
				g.fillRect(y * TILE_WIDTH, x * TILE_HEIGHT,  TILE_WIDTH, TILE_HEIGHT);
				g.setColor(Color.gray);
				g.fillRect(y * TILE_WIDTH+1, x * TILE_HEIGHT +1,  TILE_WIDTH, TILE_HEIGHT);
			}
		}
		for (int index =0; index < roomsVisited.size(); index++)
		{
			int x = roomsVisited.get(index).getRow();
			int y = roomsVisited.get(index).getColumn();
			g.setColor(Color.red);
			g.fillRect(y * TILE_WIDTH +1, x * TILE_HEIGHT +1,  TILE_WIDTH, TILE_HEIGHT);
		}
		for (int index =0; index < roomsInvalid.size(); index++)
		{
			int x = roomsInvalid.get(index).getRow();
			int y = roomsInvalid.get(index).getColumn();
			g.setColor(Color.black);
			g.fillRect(y * TILE_WIDTH +1, x * TILE_HEIGHT +1,  TILE_WIDTH, TILE_HEIGHT);
		}
		
		//draw gold on the maze
		/*for(int i =0; i < 10; i++)
		{
	        g.drawImage(drawImage("gold.png"), roomsHasGold.get(i).getColumn() * TILE_WIDTH, roomsHasGold.get(i).getRow() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null);
		}*/
		
		//draw mario image on the board
        g.drawImage(drawImage("mario.png"), p.getTitleX() * TILE_WIDTH, p.getTileY() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null);  
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
	public void actionChosen ()
	{
		String[] choices = {"Fight", "Sleep", "Run", "Search", "hide"};
	    JOptionPane.showOptionDialog(
	                               null                       // Center in window.
	                             , "What Do you want to do in this room?"  // Message
	                             , "Choose Action"            // Title in titlebar
	                             , JOptionPane.YES_NO_OPTION  // Option type
	                             , JOptionPane.PLAIN_MESSAGE  // messageType
	                             , null                       // Icon (none)
	                             , choices                    // Button text as above.
	                             , "Default"    // Default button's label
	                           );

	}
	public class Al extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_UP){
            	if(map.grid[p.getTileY()-1][p.getTitleX()] == 1){
            		/* used for gold
            		if (checkForGold(p.getTileY()-1, p.getTitleX()))
            		{
            			for (Position pos: roomsHasGold)
            			System.out.println (pos.getColumn() + " " + pos.getRow());
            			roomHasGold();
            			int i = roomsHasGold.indexOf(new Position (p.getTileY()-1, p.getTitleX()));
            			System.out.println (i);
            			roomsHasGold.remove(new Position (p.getTileY()-1, p.getTitleX()));
            			for (Position pos: roomsHasGold)
                			System.out.println (pos.getColumn() + " " + pos.getRow());
            			//System.out.println (roomsHasGold.get(i));
            		}
            		*/
        			roomsVisited.add(new Position (p.getTileY()-1, p.getTitleX())); 		
            		p.move(0, -1);
            		actionChosen ();
            		
            	}
            	else {
            		roomsInvalid.add(new Position (p.getTileY()-1, p.getTitleX()));
            		thatsWallOutput();
            	}	
			}
            if(keyCode == KeyEvent.VK_DOWN){
            	if(map.grid[p.getTileY()+1][p.getTitleX()] == 1){
            		roomsVisited.add(new Position (p.getTileY()+1, p.getTitleX()));
            		p.move(0, 1);
            	}
            	else {
            		roomsInvalid.add(new Position (p.getTileY()+1, p.getTitleX()));
            		thatsWallOutput();
            	}
            }
            if(keyCode == KeyEvent.VK_LEFT){
            	if(map.grid[p.getTileY()][p.getTitleX()-1] == 1){
            		roomsVisited.add(new Position (p.getTileY(), p.getTitleX()-1));
            		p.move(-1, 0);
            	}
            	else {
            		roomsInvalid.add(new Position (p.getTileY(), p.getTitleX()-1));
            		thatsWallOutput();
            	}
            }
            if(keyCode == KeyEvent.VK_RIGHT){
            	if(map.grid[p.getTileY()][p.getTitleX()+1] == 1){
            		roomsVisited.add(new Position (p.getTileY(), p.getTitleX()+1));
            		p.move(1, 0);
            	}
            	else {
            		roomsInvalid.add(new Position (p.getTileY(), p.getTitleX()+1));
            		thatsWallOutput();
            	}
            }
		}
	}
}
