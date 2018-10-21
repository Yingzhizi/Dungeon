package structures;

import ass2.Player;

/**
 * abstract class that encapsulates all objects that can prevent the player
 * from moving onto a square containing a structure. These must be checked 
 * before the player moves.
 *
 */
public abstract class Structure {
	
	protected String name;
	
	public String getName() {
		return this.name;
	}
	/**
	 * Checks if the player can or cannot move to the square which contains
	 * this structure.
	 * @param player
	 * @return
	 */
	public abstract boolean isBlocked(Player player);
}