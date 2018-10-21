package WinCondition;

import ass2.Dungeon;
import ass2.Player;

public class NoWin implements WinCondition {

	public boolean checkWin(Dungeon d, Player player) {
		return false;
	}

}
