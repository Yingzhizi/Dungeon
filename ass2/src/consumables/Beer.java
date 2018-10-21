package consumables;

import ass2.Entity;
import ass2.Player;
import ass2.Status;

/**
 * kind of entity can be picked up by player
 * once the player pick up the beer, the player character has one extra life
 *
 */
public class Beer extends Entity{
	
	public Beer() {
		this.name = "Beer";
	}
	
	@Override
	public boolean doAction(Player player) {
		player.gainLife();
		return true;
	}
}
