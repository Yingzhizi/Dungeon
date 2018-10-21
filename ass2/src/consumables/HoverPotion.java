package consumables;

import ass2.Player;
import ass2.Status;

/**
 * if player pick up this potion, the player can fly over the pit
 *
 */
public class HoverPotion extends Consumable {

	public HoverPotion() {
		this.name = "HoverPotion";
	}
	
	@Override
	public boolean doAction(Player player) {
		player.setStatus(Status.HOVER);
		return true;
	}

}
