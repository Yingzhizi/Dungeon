package WinCondition;

import ass2.Dungeon;
import ass2.Player;
import exits.Exit;

public class ExitWin implements WinCondition {
	
	private WinCondition win;
	
	public ExitWin(WinCondition win) {
		this.win = win;
	}
	
	/**
	 * get the player's position, if the player's position is the same as the position of the exit
	 * which means it meet the requirement of win condition, return true
	 * otherwise, return false
	 */
	@Override
	public boolean checkWin(Dungeon d, Player player) {
		if(win.checkWin(d, player)) {
			for (int i = 0; i < d.getSize(); i++) {
				for (int j = 0; j < d.getSize(); j++) {
					if (d.getSquare(i, j).getEntity() instanceof Exit) {
						if (player.getX() == j && player.getY() == i) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return win.toString() + "Reach the exit\n";
	}

}
