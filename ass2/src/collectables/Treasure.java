package collectables;

import ass2.Player;

/**
 * treasure is collectable by player character
 * in some situation, player can complete the game by picking up all the treasures
 *
 */
public class Treasure extends Collectable {
	
	public Treasure() {
		this.maxCapacity = 999;
		this.name = "Treasure";
	}

	@Override
	public boolean doAction(Player player) {
		player.addItem(this);
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
}
