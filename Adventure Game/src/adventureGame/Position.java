package adventureGame;

public class Position {
	protected int row, column;

	public Position() {
		row = 0;
		column = 0;
	} 

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	} 
	
	public int getRow() {
		return row;
	} 
	
	public int getColumn() {
		return column;
	} 
	 public boolean equals( Position pos )
	 {
	      if(this.getColumn() == pos.getColumn() && this.getRow() == pos.getRow())
	    	  return true;
	      return false;
	 }

} 

