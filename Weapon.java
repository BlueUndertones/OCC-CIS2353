package adventureGame;

public class Weapon extends Items {

	private String weaponName; // knife, fists, etc.,
	public Weapon(String wName, String name, String desc, int dmg) {
		super(name, desc, dmg);
		super.setItemName(weaponName);
	}

}
