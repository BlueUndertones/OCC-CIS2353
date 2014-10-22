package adventureGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	private int tileX, tileY ; 
	private Image player;
	public Player()
	{
		ImageIcon img = new ImageIcon ("mario.png");
		player = img.getImage();
		
		tileX= 0;
		tileY =0;
	}
	public Image getPlayer ()
	{
		return player;
	}
	
	public int getTitleX(){
		return tileX;
	}
	public int getTileY(){
		return tileY;
	}
	public void move(int dx, int dy)
	{
		tileX += dx;
		tileY += dy;
	}
}
