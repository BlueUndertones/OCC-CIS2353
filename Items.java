package adventureGame;

public class Items {

	private String itemName; // weapon, armor, Potion, spell
	private String description;
	private int damage;
	
	public Items(String name,String desc, int dmg)
	{
		name= itemName;
		desc = description;
		dmg=damage;		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
