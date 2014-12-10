package adventureGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Map 
{
	private Scanner scan;
	public static final byte WALL = 0;
    public static final byte CORRIDOR = 1;
    public static final byte PATH = 9;
    public static final byte DEAD_END = 2;
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    protected byte[ ][ ] grid;
    ArrayList <Position> rooms = new  <Position> ArrayList();
    ArrayList <Position>  walls = new  <Position> ArrayList();;
	public Map()
	{
		openFile();
		readFile();
	}
	public void openFile()
	{
		try {
			scan = new Scanner(new File ("../maze.txt"));
		}
		catch (Exception e) {
			System.out.println ("Maze not Found");
		}
	}
	public void readFile()
	{
		grid = new byte [ROWS][COLUMNS];                    
		for (int i = 0; i < ROWS; i++)                                   
			for (int j = 0; j < ROWS; j++)
		    {
		    	grid [i][j] = scan.nextByte();
		    	
		    	if (grid [i][j] == CORRIDOR)
		    	{
		    		rooms.add (new Position(i,j));
		    	}
		    	if (grid [i][j] == WALL)
		    	{
		    		walls.add (new Position(i,j));
		    	}
		    	if (grid [i][j] != WALL && grid [i][j] != CORRIDOR && grid [i][j] != 9 )
		        	throw new NumberFormatException ("At position (" + i + ", " + j + "), " +
		            	grid [i][j] + " should be " +
		                				WALL + " or " + CORRIDOR + ".");
		     }
		//scan.close();
	}
	public String toString () 
    {
 	    String result = "\n";

        for (int row = 0; row < grid.length; row++) 
        {
             for (int column = 0; column < grid [0].length; column++)
                  result += String.valueOf (grid [row][column]) + ' ';
             result += "\n";
        } // for row = 0
        return result;

     }
}
