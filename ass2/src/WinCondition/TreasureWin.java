package WinCondition;

import ass2.Dungeon;
import ass2.Player;

public class TreasureWin implements WinCondition {

	private WinCondition win;
	
	public TreasureWin(WinCondition win) {
		this.win = win;
	}
	
	/**
	 * if the number of treasure be set equal to the number of treasure in player's inventory, return true
	 * otherwise, return false
	 */
	@Override
	public boolean checkWin(Dungeon d, Player player) {
		if(win.checkWin(d, player)) {	
			if (d.getTreasuresNum() == 0 && player.getCollectTreasure() != 0) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return win.toString() + "Collect all treasure\n";
	}
}
