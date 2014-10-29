package adventureGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Maze {
    Player p = new Player("Mario");
	public static void main(String[] args) {
		new Maze();
		Map map = new Map();
	    
	}
	public Maze()
	{
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout (1,1));
		
		JPanel infoPanel = new JPanel();
		JLabel button = new JLabel(p.toString());
		infoPanel.add(button);
		
		frame.add(new Board());
		frame.add(infoPanel);

		frame.setTitle("Advanture Game");
		frame.setSize(1240, 640);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
