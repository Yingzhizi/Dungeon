package WinCondition;

import ass2.Dungeon;
import ass2.Player;

public class SwitchWin implements WinCondition {	
	
	private WinCondition win;
	
	public SwitchWin(WinCondition win) {
		this.win = win;
	}
	
	
	/**
	 * check the status of every switches in the dungeon, if all the switch been activated
	 * meet the win condition requirement
	 */
	@Override
	public boolean checkWin(Dungeon d, Player player) {
		if(win.checkWin(d, player)) {	
			if (d.checkSwitchStatus()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return win.toString() + "Activate switches\n";
	}
}
