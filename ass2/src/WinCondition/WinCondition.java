package WinCondition;

import ass2.Dungeon;
import ass2.Player;

public interface WinCondition {

	/**
	 * check if the player meet the win condition or not
	 * @param d, the dungeon
	 * @param palyer, the player character plays the dungeon
	 * @return
	 */
	public abstract boolean checkWin(Dungeon d, Player player);
}