package adventureGame;

import java.io.File;
import java.util.Scanner;

public class Map 
{
	private Scanner scan;
	public static final byte WALL = 0;
    public static final byte CORRIDOR = 1;
    public static final byte PATH = 9;
    public static final byte DEAD_END = 2;
    
    protected Position start, finish;
    protected byte[ ][ ] grid;
    int rows = 0;
    int columns = 0;
	public Map()
	{
		openFile();
		readFile();
	}
	public void openFile()
	{
		try {
			scan = new Scanner(new File ("C:\\Users\\NADHIR\\Desktop\\ch05\\ch5\\Maze\\maze.txt"));
		}
		catch (Exception e) {
			System.out.println ("Maze not Found");
		}
	}
	public void readFile()
	{
		rows = scan.nextInt();
	    columns = scan.nextInt();
	          
		grid = new byte [rows][columns];               
		start = new Position (scan.nextInt(), scan.nextInt());                
		finish = new Position (scan.nextInt(), scan.nextInt());     
		for (int i = 0; i < rows; i++)                                   
			for (int j = 0; j < columns; j++)
		    {
		    	grid [i][j] = scan.nextByte();
		    	if (grid [i][j] != WALL && grid [i][j] != CORRIDOR)
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
