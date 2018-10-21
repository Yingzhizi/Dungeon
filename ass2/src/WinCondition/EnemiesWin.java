package WinCondition;

import java.util.ArrayList;

import Enemies.Enemy;
import ass2.Dungeon;
import ass2.Player;

/**
 * one kind of win condition
 * EnemiesWin allow player win the game by destroy all the enemies
 */
public class EnemiesWin implements WinCondition{

	private WinCondition win;
	
	public EnemiesWin(WinCondition win) {
		this.win = win;
	}	
	
	/**
	 * check if there has anymore enemies in the dungeon
	 * if there doesn't exist any enemies, means all enemies been killed
	 */
	@Override
	public boolean checkWin(Dungeon d, Player player) {
		if(win.checkWin(d, player)) {
			ArrayList<Enemy> enemies = d.getEnemies();
			if (enemies.size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return win.toString() + "Kill all enemies";
	}

}
