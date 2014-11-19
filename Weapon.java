package adventureGame;
public class Weapon extends Items {

	private int damage;
	private int durability;

	public Weapon(String name, String desc, int dmg, int dur) {
		super(name, desc);
		setDamage(dmg);
		setDurability(dur);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

}
