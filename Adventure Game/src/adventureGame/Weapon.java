package adventureGame;
public class Weapon extends Items {

	private int damage = 0;
	private int durability = 0;
	private String name  ="";
	public Weapon(String name, String desc, int dmg, int dur) {
		super(name, desc);
		this.name = name;
		setDamage(dmg+1);
		setDurability(dur);
	}
	public String getName ()
	{
		return this.name;
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
