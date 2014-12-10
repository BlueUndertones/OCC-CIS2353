package adventureGame;
import java.util.Random;

/**
   The Die class simulates a six-sided die.
*/

public class Die
{
   private int sides;   // Number of sides
   private int value;   // The die's value
   private Random rand = new Random();
   
   /**
      The constructor performs an initial
      roll of the die.
      @param numSides The number of sides for this die.
   */
   
   public Die(int numSides)
   {
      sides = numSides;
      roll();
   }
   
   /**
      The roll method simlates the rolling of
      the die.
   */
   
   public int roll()
   {
      
      // Get a random value for the die.
      value = rand.nextInt(sides+1);
      return value;
   }
   
   /**
      getSides method
      @return The number of sides for this die.
   */
   
   public int getSides()
   {
      return sides;
   }
   
   /**
      getValue method
      @return The value of the die.
   */
   
   public int getValue()
   {
      return value;
   }
}
