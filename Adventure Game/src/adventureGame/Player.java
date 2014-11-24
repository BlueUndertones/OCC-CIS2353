package adventureGame;
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
    private String name;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int hitPoints = 5;
    private int gold;
    private Weapon weapons;
    private Armor armor;
    private Potion potion; 
    protected Die dice;	
    private int tileX, tileY ; 
    public boolean isHidden = false;
    
	public Player( String name )
    {
        setName(name);
        setStrength( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        setDexterity ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        setIntelligence ( numberOfDiceRolls( NUMBER_OF_ROLLS, NUMBER_OF_SIDES ) );
        //setHitPoints (  );
        tileX= 0;
		tileY = 1;
    }
	public Player()
    {
    	
    }
    public int getTitleX(){
		return tileX;
	}
	public int getTileY(){
		return tileY;
	}
	public void move(int dx, int dy)
	{
		tileX += dx;
		tileY += dy;
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
		public void setName() {
			// TODO Auto-generated method stub
			
		}	    
	    private void checkForName( String name )
	    {
	        if ( name.isEmpty() )
	        {
	            throw new IllegalArgumentException();
	        }
	    }

	    public void setStrength( int strength )
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
	    public void setDexterity( int dexterity  )
	    {
	       this.dexterity = dexterity;
	    }
	    public void setIntelligence( int intelligence )
	    {
	        this.intelligence = intelligence;
	    }
	    public void setHitPoints(  )
	    {
	        this.hitPoints = MAX_HIT_POINTS;
	    }
	    
	    public void setHitPoints( int hitPoints  )
	    {
	        this.hitPoints = hitPoints;
	    }
	    protected void addHitPoints()
	    {
	    	if ( hitPoints == MAX_HIT_POINTS )
	    	{
	    		System.out.println("Max Health");
	    	}
	    	else 
	    	{
	    		hitPoints += 1;    		
	    	}
	    }
	    
	    protected void removeHitPoints()
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
	        
	    public void setWeapon ( Weapon weapon )
	    {
	        this.weapons = weapon;
	    }
	    public void setWeapon(int npcValue )
		{
			Items item;
			
			switch ( npcValue )
			{
			
				case 0:
				{
					item = new Weapon("Sword", "S", 10, 10 );
					this.weapons = (Weapon) item;
					break;	
				}
			
				case 1:
				{
					item= new Weapon("Two Swords", "S", 10, 10 );
					this.weapons = (Weapon) item;
					break;	
				}
				case 2:
				{
					item = new Armor("Armor", "S", 10, 10 );
					this.weapons = (Weapon) item;
					break;
				}
				
				default:
				{
					
				}
			}

		}
	    
	    public void setArmor( Armor armor )
	    {
	    	this.armor = armor;
	    }
	    public void setPotion(Potion potion )
	    {
	    	this.potion = potion; 
	    }
	    public void usePotion( )
	    {
	    	
	    	if ( this.potion != null)
	    	{
		    	if ( hitPoints < MAX_HIT_POINTS )
		    	{
		    		addHitPoints();
		    	}
	    	}
	    	
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
	    
	    public Armor getArmor()
	    {
	    	return this.armor;
	    }
	    
    
    public boolean getHiden() {
		return isHidden;
	}
	public void setHiden(boolean hiden) {
		this.isHidden = hiden;
	}
	
   
    public String toString()
    {
        String stats = "<html>Player: " + this.getName() +
                        " \n<br>Hit Points: " + this.getHitPoints() +
                        " \n<br>Intelligence: " + this.getIntelligence() +
                        " \n<br>Dexterity: " + this.getDexterity() + 
                        " \n<br>Strength: " + this.getStrength() +
                        " \n<br>Gold: " + this.getGold() +
                        "\n<br>Weapon: " + this.getWeapon() +
	                    "\n<br>Armor: " + this.getArmor() +
                        "\n<br>-----------------------------</html>";
        return stats;
    } 
}
