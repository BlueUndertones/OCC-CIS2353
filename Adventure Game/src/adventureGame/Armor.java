

package adventureGame;
public class Armor extends Items{

	private int damage;
	private int durability;
	
	public Armor(String name, String desc, int dmg, int dur) {
		super(name, desc);
		setDamage(dmg);
		setDurability(dur);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		if(damage >= 0)
		{
		this.damage = damage;
		}else
		{
			throw new IllegalArgumentException(damage+ "must be greater than 0");
		}
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

}
