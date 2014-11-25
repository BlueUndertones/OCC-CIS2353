package adventureGame;
public class Potion extends Items{

	int playerHitPoints = 0;
	public Potion(String name, String desc, int playerHitPoints) {
		super(name, desc);
      this.playerHitPoints = playerHitPoints++;
		}

}
