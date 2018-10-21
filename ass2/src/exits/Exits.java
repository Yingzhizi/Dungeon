package exits;

import ass2.Entity;
import ass2.Player;

/**
 * probs needs a rename/better description or maybe even refactored into
 * a different class
 * 
 * abstract class encapsulating all entities that cause the player to
 * "exit" the game when walking on the square, includes pit(falling in 
 * and dying) and exit(which causes the player to win) 
 */

public abstract class Exits extends Entity {

	public abstract boolean doAction(Player player);
}
