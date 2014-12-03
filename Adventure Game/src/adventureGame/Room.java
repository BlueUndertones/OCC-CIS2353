package adventureGame;
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
	private boolean IsVisited = false;
	private String Name;
	private String Description;
   	public Sequence<NPC> NPCs = new <NPC> Sequence();

   public Room(int x, int y)
   {
      X = x;
      Y = y;
      IsVisited = false;
      setRoomDesc();
      setNPCs();
      setGold();
   }
   
    public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
    public void setGold()
    {
      //randomly roll gold 1d20
      Die die = new Die(20);
      GoldPieces = die.roll();
   }
   
   public int getGold()
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
    	  NPCs.append(new NPC (NPC.getNpcName(i)));
      }
   }
   public Sequence<NPC> getNPCs ()
   {
	   return NPCs;
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
            roomNum = 1;
            Name = "Slime Cavern";
            break;
         }
         case 2:
         {
            roomNum = 2;
            Name = "Mess Hall";
            break;
         } 
         case 3:
         {
            roomNum = 3;
            Name = "Hallway";
            break;
         } 
         case 4:
         {
            roomNum = 4;
            Name = "Mist Cavern";
            break;
         } 
         case 5:
         {
            roomNum = 5;
            Name = "Sleeping Quarters";
            break;
         } 
         case 6:
         {
            roomNum = 6;
            Name = "Cellar";
            break;
         } 
         default:
        	 Name = "Not valid.";
      } 
         roomFilename = "room" + roomNum + ".txt";
         Description = getRoomFile(roomFilename).toString();
         return Description;
   }
   
   public String getRoomFile(String roomFilename)
   {
      File file = new File(roomFilename);
      String line ="";
      try
      {
         Scanner scan = new Scanner(file);
                  
         while (scan.hasNextLine())
         {
            StringBuilder sb = new StringBuilder();
            line = scan.nextLine();
   
               sb.append(line);
         }
         scan.close();
      }
      catch(FileNotFoundException be)
      {}
      return line; 
   }
   
   /*
      ...
   */
   public String toString()
   {
	   String desc = "<html>" + Name +
                       " \n<br>" + Description + "</html>";
      return desc;
   }
}
