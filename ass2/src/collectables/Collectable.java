package collectables;

import ass2.Entity;
import ass2.Player;

/**
 * This abstract class encapsulate all items that can be picked up by the player 
 * and stored into their inventory. Treasure, Keys, Bombs, Sword, Arrows
 */

public abstract class Collectable extends Entity {

	/**
	 * Maximum number of a particular item a player's inventory can hold.
	 */
	protected int maxCapacity;

	/**
	 * Function to pick up item and add it to the player's inventory
	 * @param player
	 * @return boolean if item is able to be picked up (doesn't exceed player's
	 * max capacity for that item)
	 */
	@Override
	public abstract boolean doAction(Player player);
	
	
}
