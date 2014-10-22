package adventureGame;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	private Timer timer;
	private Map map;
	private Player p;
	public Board () {
		map = new Map();
		p = new Player();
		addKeyListener(new Al());
		setFocusable(true);
		timer = new Timer (25, this);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint (Graphics g)
	{
		super.paint(g);
		
        
		//ImageIcon img = new ImageIcon ("C:\\Users\\NADHIR\\Desktop\\ch05\\ch5\\Maze\\mario.png");
		//g.drawImage(img.getImage(), 32, 32, this  );
		for (int x=0; x < map.columns;x++)
			for (int y =0; y < map.rows; y++){
				if (map.grid[y][x] == 1)
				{
					
					g.setColor(Color.white);
					g.fillRect(x * 60, y *60,  60, 60);
					g.setColor(Color.red);
					g.fillRect(x * 60+1, y *60 +1,  60, 60);
				}
				if (map.grid[y][x] == 0)
				{
					
					g.setColor(Color.white);
					g.fillRect(x * 60, y *60,  60, 60);
					g.setColor(Color.black);
					g.fillRect(x * 60+1, y *60+1,  60,60);
				}
			}
		//prepare a Mario Image source
        Image image = null;
		try {
			image = ImageIO.read(this.getClass().getResource("mario.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

        g.drawImage(image, p.getTitleX() * 60, p.getTileY() * 60, 60, 60, null);
        
        //repaint();
	}
	public class Al extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_W){
            	if(map.grid[p.getTitleX()][(p.getTileY()-1)] == 1){
            		p.move(0, -1);
            	}
			}
            if(keyCode == KeyEvent.VK_S){
            	if(map.grid[p.getTitleX()][(p.getTileY()+1)] == 1){
            		p.move(0, 1);
            	}
            }
            if(keyCode == KeyEvent.VK_A){
            	if(map.grid[(p.getTitleX()-1)][p.getTileY()] == 1){
            		p.move(-1, 0);
            	}
            }
            if(keyCode == KeyEvent.VK_D){
            	if(map.grid[(p.getTitleX()+1)][p.getTileY()] == 1){
            		p.move(1, 0);
            	}
            }
            
			
            repaint();
		}
		public void keyReleased (KeyEvent e)
		{
			
		}
		public void keyTyped (KeyEvent e)
		{
			
		}
	}
}
