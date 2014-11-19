/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DeMario
 */
public class Player implements character
{   
    private final int MAX_HIT_POINTS = 20;
    private final int NO_HIT_POINTS = 0;
    private final int NUMBER_OF_ROLLS = 3; 
    private final int NUMBER_OF_SIDES = 6;
    protected String name;
    protected int strength, dexterity, intelligence, hitPoints = 5, gold;
    protected Object weapons;
    protected Die dice;

   
    public Player( String name )
    {
        setName(name);
        setStrength( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        setDexterity ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        setIntelligence ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        //setHitPoints (  );
    }
    public void setName( String name )
    {
         try
        {
           checkForName( name ); 
        }catch (IllegalArgumentException e )
        
        {
          System.out.println("Enter a valid name");
        }          
        finally 
        {
            this.name = name;
        }
    }
    
    private void checkForName( String name )
    {
        if ( name.isEmpty() )
        {
            throw new IllegalArgumentException();
        }
    }

    private void setStrength( int strength )
    {   
        this.strength = strength;
    }
    
    protected int numberOfDiceRolls( int numberOfRolls, int numberOfSides )
    {
        int value = 0;
        int rolls = 1;  
        dice = new Die( numberOfSides );             
              
            while( rolls <= numberOfRolls )
            {
                dice.roll();
                rolls++;
                value += dice.getValue();
            }       
        
        
        return value;
    }
    private void setDexterity( int dexterity  )
    {
       this.dexterity = dexterity;
    }
    private void setIntelligence( int intelligence )
    {
        this.intelligence = intelligence;
    }
    private void setHitPoints(  )
    {
        this.hitPoints = MAX_HIT_POINTS;
    }
    public void addHitPoints()
    {
    	if ( hitPoints == MAX_HIT_POINTS )
    	{
    		System.out.println("Max Health");
    	}
    	else 
    	{
    		hitPoints += 5;    		
    	}
    }
    
    public void removeHitPoints()
    {
    	if ( hitPoints == NO_HIT_POINTS )
    	{
    		System.out.println("Player is dead");
    	}
    	else 
    	{
    		hitPoints -= 5;    		
    	}
    }
    public void setGold( )
    {
        
    }
        
    public void setWeapon ( Object item )
    {
        
    }
    public String getName ()
    {
        return this.name;
        
    }
    public int getStrength()
    {
        return this.strength;
    }
    public int getDexterity()
    {
        return this.dexterity;
    }
    public int getIntelligence()
    {
        return this.intelligence;
    }
    public int getHitPoints()
    {
        return this.hitPoints;
    }
    public int getGold()
    {
        return this.gold;
    }
    public Object getWeapon()
    {
        return this.weapons;
    }
    
    public String toString()
    {
        String stats = "Player: " + name +
                        " \nHealth: " + hitPoints +
                        " \nIntelligence: " + intelligence +
                        " \nDexterity: " + dexterity + 
                        " \nStrength: " + strength +
                        " \nGold: " + gold +
                        "\n-----------------------------";
        return stats;
    }
 
}
