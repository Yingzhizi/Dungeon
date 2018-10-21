package consumables;

import ass2.Entity;
import ass2.Player;

/**
 * Abstract class encapsulating items that are immediately used when the player
 * walks on the square with this item.
 */
public abstract class Consumable extends Entity {

	/**
	 * Uses the consumable item (changes the player's state)
	 * @param player
	 */
	@Override
	public abstract boolean doAction(Player player);
	
}
