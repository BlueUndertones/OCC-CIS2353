package adventureGame;

import javax.swing.JFrame;

public class Maze {

	public static void main(String[] args) {
		new Maze();
		Map map = new Map();
		Player p = new Player();
		System.out.println(p.getTitleX() + " " + (p.getTileY()));
		System.out.println(map.grid[p.getTitleX()][p.getTileY()]);
		System.out.println(map.toString());

	}
	public Maze()
	{
		JFrame f = new JFrame();
		f.setTitle("Advanture Game");
		f.add(new Board());
		f.setSize(505, 520);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
