package consumables;

import ass2.Player;
import ass2.Status;

/**
 * If player character pick up this potion, the player character is invincible to all enemies
 *
 */
public class InvincibilityPotion extends Consumable {

	public InvincibilityPotion() {
		this.name = "InvincibilityPotion";
	}
	
	@Override
	public boolean doAction(Player player) {
		player.setStatus(Status.INVINCIBLE);
		return true;
	}
}