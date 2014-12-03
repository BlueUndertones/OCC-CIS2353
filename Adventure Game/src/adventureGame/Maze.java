package adventureGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Maze {
	
    Board board = new Board();
    static JFrame frame = new JFrame();
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel southPanel = new JPanel (new GridLayout(1,2));
    JPanel party = new JPanel ();
    JPanel roomDesc = new JPanel ();
    
	public static void main(String[] args) {
		new Maze();
	}
	
	public Maze()
	{
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
				 new Runnable() {
				  @Override
				  public void run() {
				    SwingUtilities.invokeLater(new Runnable() {
				      @Override
				      public void run() {
				    	  mainPanel.add(board.buildNpcInfo(), BorderLayout.EAST);
				    	  mainPanel.revalidate();
				    	  mainPanel.add(board.buildPlayerInfo(),BorderLayout.WEST);
				    	  mainPanel.revalidate();
				    	  
				    	  party.removeAll();
				    	  party.add(board.buildRoomInfo());
				    	  party.revalidate();
				    	  
				    	  roomDesc.removeAll();
				  		  roomDesc.add(board.partyPanel());
				  		  roomDesc.revalidate();
				    	  
				    	  southPanel.add(roomDesc);
				  		  southPanel.add(party);
				  		  southPanel.revalidate();
				    
				    	  mainPanel.add(southPanel,BorderLayout.SOUTH);
				    	  mainPanel.revalidate();
				       }
				    });
				  }
				}, 0, 1, TimeUnit.SECONDS);
		
		frame.setLayout(new GridLayout (1,1));
		
		
		party.add(board.buildRoomInfo());
		roomDesc.add(board.partyPanel());
		
		southPanel.add(roomDesc);
		southPanel.add(party);
		
		mainPanel.add(board.buildNpcInfo(), BorderLayout.EAST);
		mainPanel.add(board.buildPlayerInfo(),BorderLayout.WEST);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		JPanel center = new JPanel(new GridLayout(0,1));
		center.add(board,BorderLayout.CENTER);
		
		mainPanel.add(center);

		frame.add(mainPanel);
			
		frame.setTitle("Advanture Game");
		frame.setSize(900, 740);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
