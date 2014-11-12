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

import java.util.*;
import java.io.*;

public class Room {
   // current room location in maze...
	private int X;
	private int Y;

   private int GoldPieces;
   private boolean IsBoss;
	private boolean IsVisited;
	private String Name;
	private String Description;
   public ArrayList<NPC> NPCs;

   public Room(int x, int y)
   {
      X = x;
      Y = y;
      IsVisited = false;
      setRoomDesc();
      setNPCs();
      setGold();
   }
   
   private void setGold()
   {
      //randomly roll gold 1d20
      Die die = new Die(20);
      GoldPieces = die.roll();
   }
   
   private int getGold()
   {
      return GoldPieces;
   }

   private void setNPCs()
   {
      // need roll of 0-6
      Die die = new Die(7);
      int numNPCs = die.roll() - 1;
      for (int i=0; i< numNPCs; i++)
      {
         NPCs.add(new NPC("Monster")); //need to pass string here, like name of NPC
      }
   }

   public void clearNPCs()
   {
      NPCs.clear();
   }

   // This method should be called by the code that moves the player into a new room, as the player
   // leaves the current room.
   // else with that room.
   // i.e. previousRoom.visited()
   public void visited()
   {
      IsVisited = true;
      GoldPieces = 0;
      clearNPCs();
   }   

   // this method reports whether or not the room has been visited...
   public boolean isVisited()
   {
      return IsVisited;
   }
   
   public void isBoss()
   {
      //if room is last room @ ???
      if(X == 9 && Y == 8)
      {
         IsBoss = true;
      }
      else
      {
         IsBoss = false;
      }
   }
   
   /*
      this will choose 1 of 6 room descriptions
   */
   public String setRoomDesc()
   {
      String roomFilename;

      Die d = new Die(6);
      int roomNum = d.roll();
      switch(roomNum)
      {
         case 1:
         {
            Name = "Slime Cavern";
            break;
         }
         case 2:
         {
            Name = "Mess Hall";
            break;
         } 
         case 3:
         {
            Name = "Hallway";
            break;
         } 
         case 4:
         {
            Name = "Mist Cavern";
            break;
         } 
         case 5:
         {
            Name = "Sleeping Quarters";
            break;
         } 
         case 6:
         {
            Name = "Cellar";
            break;
         } 

         roomFilename = "room" + roomNum + ".txt";
         Description = getRoomFile(roomFilename).toString();
      } 
   }
   
   public String getRoomFile(String filename)
   {
      List<String> lines = File.readAllLines(filename);
      StringBuilder sb = new StringBuilder();
      for(String s: lines) {
         sb.append(s);
      }
      return sb.toString();
   }
   
   /*
      ...
   */
   public String toString()
   {
      return (Name + "/n" + Description);
   }
}