package ass2;

import java.util.ArrayList;
import Enemies.Enemy;
import collectables.*;

public class Dungeon {
	
	/**
	 * Holds the dungeon as a 2D array of Squares (or tiles)
	 * size is the dimensions of the dungeon (size x size)
	 * player is the the player's character
	 * switches hold the list of the switches in the dungeon
	 * win conditions hold the list of the win conditions
	 */
	private Square[][] dungeon;
	private int size;
	
	/**
	 * constructor class
	 * @param dungeon
	 * @param size
	 */
	public Dungeon(Square[][] dungeon, int size) {
		this.dungeon = dungeon;
		this.size = size;
	}
	
	/**
	 * get the 2-d array of squares from the dungeon
	 * @return
	 */
	public Square[][] getDungeon() {
		return dungeon;
	}
	
	
	//current bullshit function that does print enemy
	public boolean anyEnemyPrintable(ArrayList<Enemy> enemyList, int y, int x) {
		if(enemyList.size() != 0) {
			for(Enemy e:enemyList) {
				if(e.getxCoord() == x && e.getyCoord() == y) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * get the specific coordination square from the dungeon
	 * @param x, x coordinate
	 * @param y, y coordinate
	 * @return
	 */
	public Square getSquare(int x, int y) {
		return this.dungeon[x][y];
	}
	
	
	/**
	 * get the size of the dungeon
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * get the list of enemies in the dungeon
	 * @return
	 */
	public ArrayList<Enemy> getEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (dungeon[i][j].isEnemy()) {
					enemies.addAll(dungeon[i][j].getEnemies());
				}
			}
		}
		return enemies;
	}

	/**
	 * get the number of treasures in the dungeon
	 * @return
	 */
	public int getTreasuresNum() {
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (dungeon[i][j].getEntity() instanceof Treasure) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * check the status of all the switch in the dungeon
	 * if all the switch been activated, return true
	 * otherwise, return false
	 * @return
	 */
	public boolean checkSwitchStatus() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (dungeon[i][j].getSwitch() != null) {
					if (dungeon[i][j].getSwitch().open() == false) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
