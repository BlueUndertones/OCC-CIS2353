

package adventureGame;
public class Armor extends Items{

	private int damage = 0;
	private int durability = 0;
	
	public Armor(String name, String desc, int dmg, int dur) {
		super(name, desc);
		setDamage(dmg - 1);
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
