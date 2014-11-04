import java.util.*;
import java.io.*;

public class Maze
{  
   private Room room = new Room();

   
   public Maze()
   {
   int currentRoomNumber = 0;
	int currentRow = 0;
	int currentColumn = 0;
	//open the map file
   
//    	while (file.hasnextline) {
//    		line = readline;
//    		currentRow++;
   	    //split into rooms (string.split())
   // 	    for each room in line {
   // 	    	currentColumn++;
   // 	        if (room > 0) {
   // 	         	currentRoomNumber++;
   // 				CreateNewRoom(roomNumber, currentColumn, currentRow);
   // 			    map[currentRow][currentColumn] = room
   // 			}
   // 			else {
   // 				// no room here!
   // 				map[currentRow][currentColumn] = null;
   // 			}
   // 		}	
   	   //}
   }

//    public void DescribeRoom(Room room, int roomNumber)
//    {
//        File file = openfile("room" + currentRoomNumber + ".txt");
//        room.name = file.ReadLine();
//        while (file.hasNextLine()) {
//        	room.description = room.description + file.ReadLine();
//        }
//    }
   
//    public Room CreateNewRoom(int roomNumber, int currentColumn, int currentRow)
//    {
//        Room room = new Room();
//        room.x = currentColumn;
//        room.y = currentRow;
//    
//        DescribeRoom(currentRoomNumber);
//    
//        room.IsVisited = false;
//        room.IsEntrance = (currentRoomNumber == 1);
//        room.IsBoss = (currentRow == 9 && currentColumn == 9);
//        Dice myDice = new Dice(20);
//        room.GoldPieces = myDice.Roll();
//    
//    	int numNPC = CalculateNumberOfNPC(room.IsBoss);
//    
//        for (int i=0; i < numNPC; i++) {
//        	Character npc = new Character(room.IsBoss);	
//        	room.NonPlayers.Add(npc);
//        }
//        
//        return room;
//    }
   
   public int CalculateNumberOfNPC()
   {
       //Dice myDice;
       if (room.IsBoss) {
       	// roll 1-6
	      Dice myDice = new Dice(6); // 6-sided die (1-6)
	      int roll = myDice.Roll();
       	return roll;
      	}
      	else {
	      Dice myDice = new Dice(7); // 7-sided die (0-6)
	      int rollWithZero = myDice.Roll() - 1;
         return rollWithZero;
   	}
   }
   
//    public Room MazeCanMoveNorth() {
//    	return map[currentRoom.y-1][currentRoom.x];	
//    }
//    
//    public Room MazeCanMoveSouth() {
//    	return map[currentRoom.y+1][currentRoom.x];	
//    }
//    
//    public Room MazeCanMoveEast() {
//    	return map[currentRoom.y][currentRoom.x+1];	
//    }
//    
//    public Room MazeCanMoveWest() {
//    	return map[currentRoom.y][currentRoom.x-1];	
//    }
   
   public void MazeMove(String direction, boolean isVisited)
   {
   	// handle the move.
   	// consider if the main game loop passes "NORTH", "N", "n"
   	if (direction == "north") {
   		TryMoveNorth();
   	}
   	if (direction == "south") {
   		TryMoveSouth();
   	}
   
   	if (direction == "east") {
   		TryMoveEast();
   	}
   
   	if (direction == "west") {
   		TryMoveWest();
   	}
   
//    	// handle the backtracking...
//    	if (currentRoom.isVisited == false) {
//    		// action section?
//    	}
//    	else {
//    		// TODO: remove stuff from the room too :)
//    		
//    		// tell user it's empty...
//    		System.out.println("You've been here before, it's empty!");
//    	}
   	
   	// tell user where they are... 
//    	System.out.println(CurrentRoom.Name);
//    	System.out.println(CurrentRoom.Description);
   }
   
   private void TryMoveNorth()
   {
   	Room newRoom = CanMoveNorth();
   	if (newRoom != null) 
   	{
   		Maze.currentRoom = newRoom;
   	}
   	else 
   	{
   		// can't move, tell user...
         System.out.println("There is a wall in the way.");
   	}
   }
   
   private void TryMoveSouth()
   {
   	Room newRoom = CanMoveSouth();
   	if (newRoom != null) {
   		Maze.currentRoom = newRoom;
   	}
   	else {
   		// can't move, tell user...
         System.out.println("There is a wall in the way.");
   	}
   }
   
   private void TryMoveEast()
   {
   	Room newRoom = CanMoveEast();
   	if (newRoom != null) {
   		Maze.currentRoom = newRoom;
   	}
   	else {
   		// can't move, tell user...
         System.out.println("There is a wall in the way.");
   	}
   }
   
   private void TryMoveWest()
   {
   	Room newRoom = CanMoveWest();
   	if (newRoom != null) {
   		Maze.currentRoom = newRoom;
   	}
   	else {
   		// can't move, tell user...
         System.out.println("There is a wall in the way.");
   	}
   }
}















