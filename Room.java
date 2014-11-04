/*
   What should the room do?
   -----------------------------------------
   -track how much gold is in there
   -track if the room has been visited
   -know its name and description
      -read description from file
   -know if boss room or regular room
   -return the name & description somewhere
*/
package adventuregame;

import java.util.*;

public class Room {
	private int X;//?
	private int Y;//?
   private int GoldPieces;
   private boolean IsBoss;
	private boolean IsVisited;
	private String Name;
	private String Description;
   
   public Room()
   {
      //track which room is randomly set to what so when you move out it is the same when you come back.
   }
   
   public void setGold()
   {
      //randomly roll gold 1d20
      //remember that value
   }
   
   public void isVisited()
   {
      //track if room has been visited
      //if so clear room on way out
   }
   
   public void isBoss()
   {
      //if room is last room @ ???
         //isBoss = true;
      //else
         //isBoss = false;
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
            roomDesc = "room1.txt";	//read what's in this file
            break;
         }
         case 2:
         {
            roomDesc = "room2.txt";	//read what's in this file
            break;
         } 
         case 3:
         {
            roomDesc = "room3.txt";	//read what's in this file
            break;
         } 
         case 4:
         {
            roomDesc = "room4.txt";	//read what's in this file
            break;
         } 
         case 5:
         {
            roomDesc = "room5.txt";	//read what's in this file
            break;
         } 
         case 6:
         {
            roomDesc = "room6.txt";	//read what's in this file
            break;
         } 
      } 
   }
   
   /*
      ...
   */
   public String toString()
   {
      String name = "Whatever";
      String description = name + "/n" +
                           "This is just a dummy description";
      return description;
   }
}