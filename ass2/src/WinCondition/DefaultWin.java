package WinCondition;

import ass2.Dungeon;
import ass2.Player;

public class DefaultWin implements WinCondition {

	@Override
	public boolean checkWin(Dungeon d, Player player) {
		return true;
	}
	
	@Override
	public String toString() {
		return "";
	}

}
