package adventureGame;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private Map map;
	private Player p;
	Random ran = new Random ();
	ArrayList <Integer> num = new <Integer> ArrayList () ;
	public Board () {
		map = new Map();
		p = new Player("Mario");
		saveRandomRoomNumbers ();
		addKeyListener(new Al());
		setFocusable(true);
		timer = new Timer (25, this);
		timer.start();
	}
	//save random room numbers to draw the random gold on the maze
	public void saveRandomRoomNumbers ()
	{
		for (int i =0; i < 10; i++)
			num.add (ran.nextInt(map.rooms.size()));
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
				if (map.grid[x][y] == 1)
				{
					g.setColor(Color.white);
					g.fillRect(y * 60, x *60,  60, 60);
					g.setColor(Color.red);
					g.fillRect(y * 60+1, x *60 +1,  60, 60);
				}
				if (map.grid[x][y] == 0)
				{
					g.setColor(Color.white);
					g.fillRect(y * 60, x *60,  60, 60);
					g.setColor(Color.black);
					g.fillRect(y * 60+1, x *60+1,  60,60);	
				}
			}
		}
		//draw gold on the maze
		for(int i =0; i < 10; i++)
		{
	        g.drawImage(drawImage("gold.png"), map.rooms.get(num.get(i)).getColumn() * 60, map.rooms.get(num.get(i)).getRow() * 60, 60, 60, null);
		}
		//draw mario image on the board
        g.drawImage(drawImage("mario.png"), p.getTitleX() * 60, p.getTileY() * 60, 60, 60, null);  
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
	
	public class Al extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_UP){
            	if(map.grid[p.getTileY()-1][(p.getTitleX())] == 1 ){
            		p.move(0, -1);
            	}
			}
            if(keyCode == KeyEvent.VK_DOWN){
            	if(map.grid[p.getTileY()+1][(p.getTitleX())] == 1){
            		p.move(0, 1);
            	}
            }
            if(keyCode == KeyEvent.VK_LEFT){
            	if(map.grid[(p.getTileY())][p.getTitleX()-1] == 1){
            		p.move(-1, 0);
            	}
            }
            if(keyCode == KeyEvent.VK_RIGHT){
            	if(map.grid[(p.getTileY())][p.getTitleX()+1] == 1){
            		p.move(1, 0);
            	}
            }
            repaint();
		}
	}
}
