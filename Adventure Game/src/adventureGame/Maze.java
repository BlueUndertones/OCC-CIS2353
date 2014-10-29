package adventureGame;

import javax.swing.JFrame;

public class Maze {

	public static void main(String[] args) {
		new Maze();
		Map map = new Map();
		Player p = new Player("Mario");
	}
	public Maze()
	{
		JFrame f = new JFrame();
		f.setTitle("Advanture Game");
		f.add(new Board());
		f.setSize(620, 640);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
