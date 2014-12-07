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
   private int hitPoints = 20;
   private int gold;
   private Weapon weapons;
   private Armor armor;
   private Potion potion; 
   protected Die dice;	
   private int tileX, tileY ; 
   public boolean isHidden = false;
   private boolean playerAlive = true;
   private boolean haveArmor = false, haveWeapon = false;
   
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
   public int getTileX(){
		return tileX;
	}
	public int getTileY(){
		return tileY;
	}
	public void setTileX(int  tileX){
		this.tileX = tileX;
	}
	public void setTileY(int tileY){
		this.tileY = tileY;
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
	    	if ( isPlayerDead() )
	    	{
		    	if ( this.hitPoints == MAX_HIT_POINTS )
		    	{
		    		System.out.println("Max Health");
		    	}
		    	else 
		    	{
		    		this.hitPoints += 1;    		
		    	}
	    	}	    	
	    }
	    protected boolean isPlayerDead()
	    {
	    	if ( this.hitPoints <= 0 )
	    	{
	    		playerAlive = false; 
	    		return true;
	    	}
	    	return false;
	    }
	    protected void removeHitPoints()
	    {
	    	//playerAlive = checkForDeadPlayers();
	    	if (!isPlayerDead())
	    	{
	    		this.hitPoints -= 5; 
	    		if (isPlayerDead())
	    		{
	    			this.hitPoints = 0;
	    			playerAlive = false;
	    			System.out.println(this.getName() + " has died");
	    		}	
	    	}
	    	else 
	    	{
	    		System.out.println(this.getName() + " has died");	
	    	}
	    }
	    public void setGold( int gold )
	    {
	        this.gold = gold; 
	    }
	        
	    public void setWeapon ( Weapon weapon )
	    {
	    	if (haveWeapon)
	    	{
	    		System.out.println("Cant pick up weapon");
	    	}
	    	else
	    	{
	    		this.weapons = weapon;
		        this.haveWeapon = true;
		    }
	        
	    }
	    public void setWeapon(int npcValue )
		{
			Items item;
			
			switch ( npcValue )
			{
			
				case 0:
				{
					item = new Weapon("Sword", "S", 10, 3 );
					this.weapons = (Weapon) item;
					break;	
				}
			
				case 1:
				{
					item= new Weapon("Two Swords", "S", 10, 1 );
					this.weapons = (Weapon) item;
					break;	
				}
				case 2:
				{
					item = new Weapon("Knife", "S", 10, 1);
					//item = new Armor("Armor", "S", 10, 10 );
					//this.weapons = (Weapon) item;
					break;
				}
				case 3:
				{
					item= new Weapon("Machine gun", "S", 10, 3 );
					this.weapons = (Weapon) item;
					break;	
				}
				case 4:
				{
					item= new Weapon("Sniper", "S", 10, 2 );
					this.weapons = (Weapon) item;
					break;	
				}
				case 5:
				{
					item= new Weapon("Two Swords", "S", 10, 2 );
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
	    	if (haveArmor)
	    	{
	    		System.out.println("Cant pick up armor");
	    	}
	    	else
	    	{
		    	this.armor = armor;
		    	this.haveArmor = true;
	    	}
	    }
	    public void setPotion(Potion potion )
	    {
	    	this.potion = potion; 
	    	usePotion();
	    }
	    public void usePotion( )
	    {
	    	
	    	if ( this.potion != null)
	    	{
		    	if ( hitPoints < MAX_HIT_POINTS && hitPoints > NO_HIT_POINTS )
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
	    public Weapon getWeapon()
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
	   String w = "";
	   String a = "";
	   String g = "";
	   if(this.getWeapon() != null)
		   w = "\n<br>Weapon: " + this.getWeapon();
	   if (this.getArmor() != null)
		   a = "\n<br>Armor: " + this.getArmor();
	   if (this.getGold() != 0)
		   g = " \n<br>Gold: " + this.getGold();
       String stats = "<html>Player: " + this.getName() +
                       " \n<br>Hit Points: " + this.getHitPoints() +
                       " \n<br>Intelligence: " + this.getIntelligence() +
                       " \n<br>Dexterity: " + this.getDexterity() + 
                       " \n<br>Strength: " + this.getStrength() +
                       g + w + a +
                       "\n<br>-----------------------------</html>";
       return stats;
   } 
   
}
