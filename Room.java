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
import java.io.*;

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
      //set gold here
      setGold();
   }
   
   private void setGold()
   {
      //randomly roll gold 1d20
      Die die = new Die();
      gold = die.roll(20);
      //remember that value
      gold = GoldPieces;
   }
   
   public void isVisited()
   {
      //track if room has been visited
      //if so clear room on way out
   }
   
   public void isBoss()
   {
      //if room is last room @ ???
      if(position[9][8])
      {
         isBoss = true;
      }
      else
      {
         isBoss = false;
      }
   }
   
   /*
      this will choose 1 of 6 room descriptions
   */
   public String setRoomDesc()
   {
      Random rr = new Random( );
		//choose a random room number 1 - 6
      int roomNum = rr.nextInt( 6 ) + 1; //+1 eliminates 0
      String roomDesc = " "; //start out empty
      
      switch(roomNum)
      {
         case 1:
         {
            Name = "Slime Cavern";
            roomDesc = "room1.txt";	//read what's in this file
            return Name;
            break;
         }
         case 2:
         {
            Name = "Mess Hall";
            roomDesc = "room2.txt";	//read what's in this file
            return Name;
            break;
         } 
         case 3:
         {
            Name = "Hallway";
            roomDesc = "room3.txt";	//read what's in this file
            return Name;
            break;
         } 
         case 4:
         {
            
            Name = "Mist Cavern";
            roomDesc = "room4.txt";	//read what's in this file
            return Name;
            break;
         } 
         case 5:
         {
            Name = "Sleeping Quarters";
            roomDesc = "room5.txt";	//read what's in this file
            return Name;
            break;
         } 
         case 6:
         {
            Name = "Cellar";
            roomDesc = "room6.txt";	//read what's in this file
            return Name;
            break;
         } 
      } 
   }
   
   public void getRoomFile()
   {
      File file = new File(roomDesc);
      
   }
   
   /*
      ...
   */
   public String toString()
   {
      String description = Name + "/n" +
                           "This is just a dummy description";
      return description;
   }
}