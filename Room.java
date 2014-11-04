
package adventuregame;

import java.util.*;

public class Room {
	private int X;
	private int Y;
	private boolean IsVisited;
	private String Name;
	private String Description;
	private boolean IsEntrance;
	private boolean IsBoss;
	private int GoldPieces;
	//public ArrayList<Character> nonPlayers;
   
   public Room()
   {
   //track which room is randomly set to whagt so when you move out it is the same when you come back.
   }
   
   //BETTER HERE OR CREATE ROOM?
   public int setGold()
   {
      //randomly roll gold 1d20
      return GoldPieces;
   }
   
   //IN MAZE?
//    public NPC setNPC()
//    {
//       //randomly roll if NPC in room 0-6 NPCS possible
//       return npc;
//    }
   
   //IN MAZE?
   public boolean isVisited()
   {
      //track if room has been visited
      //if so clear room on way out
      return true;
   }
   
   /*
      this will choose 1 of 6 room descriptions
   */
   public void setRoomDesc()
   {
      Random rr = new Random( );
		//choose a random room number 1 - 6
      int roomNum = rr.nextInt( 6 ) + 1; //+1 eliminates 0
      String roomDesc = " "; //start out empty
      
      switch(roomNum)
      {
      
         case 1:
         {
            roomDesc = "room1.txt";	
            break;
         }
         case 2:
         {
            roomDesc = "room2.txt";	
            break;
         } 
         case 3:
         {
            roomDesc = "room3.txt";	
            break;
         } 
         case 4:
         {
            roomDesc = "room4.txt";	
            break;
         } 
         case 5:
         {
            roomDesc = "room5.txt";	
            break;
         } 
         case 6:
         {
            roomDesc = "room6.txt";	
            break;
         } 
         }
         
   }
   
   //CALL FOM MAZE?
   public String toString()
   {
      String name = "Whatever";
      String description = name + "/n" +
                           "This is just a dummy description";
      return description;
   }
}