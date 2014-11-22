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
	
    Board board = new Board();
    
	public static void main(String[] args) {
		new Maze();
	}
	public Maze()
	{
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout (1,1));
		
		//JPanel infoPanel = new JPanel();
		//JLabel button = new JLabel(p.toString());
		//infoPanel.add(button);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(board.buildNpcInfo(), BorderLayout.EAST);
		p.add(board.buildPlayerInfo(),BorderLayout.WEST);
		
		JPanel center = new JPanel(new GridLayout(0,1));
		center.add(board,BorderLayout.CENTER);
		
		p.add(center);
		//frame.add(board);
		//frame.add(board.buildPlayerInfo());
		frame.add(p);
			
		frame.setTitle("Advanture Game");
		frame.setSize(900, 640);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
