package adventureGame;

public class Armor extends Items{

	private String armorName; // 
	
	public Armor(String name, String desc, int dmg) {
		super(name, desc, dmg);
		super.setItemName(armorName);
	}

}
