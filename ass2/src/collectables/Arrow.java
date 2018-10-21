package collectables;

import java.util.Iterator;
import Enemies.Enemy;
import ass2.*;

/**
 * arrow is collectable and player can use it to attack enemy
 *
 */
public class Arrow extends Collectable {

	private int maxDist = 3;
	
	public Arrow() {
		this.maxCapacity = 999;
		this.name = "Arrow";
	}
	
	/**
	 * automatically pick up arrow and add it to player's inventory
	 */
	@Override
	public boolean doAction(Player player) {
		player.addItem(this);
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Method to attack an enemy on given square
	 * @param dungeon
	 * @param player
	 */
	public boolean attack(Square[][] dungeon, Player player) {
		int x = player.getX();
		int y = player.getY();
		switch(player.getDirection()) {
			case UP:
				for(int i = 0; i < maxDist; i++) {
					y--;
					if(dungeon[y][x].getStructure() != null) {
						break;
					} else if(dungeon[y][x].isEnemy()) {
						Iterator<Enemy> iterator = dungeon[y][x].getEnemies().iterator();
						iterator.next();
						iterator.remove();
						break;						
					}
				}
				break;
			case DOWN:
				for(int i = 0; i < maxDist; i++) {
					y++;
					if(dungeon[y][x].getStructure() != null) {
						break;
					} else if(dungeon[y][x].isEnemy()) {
						Iterator<Enemy> iterator = dungeon[y][x].getEnemies().iterator();
						iterator.next();
						iterator.remove();
						break;						
					}
				}
				break;
			case LEFT:
				for(int i = 0; i < maxDist; i++) {
					x--;
					if(dungeon[y][x].getStructure() != null) {
						break;
					} else if(dungeon[y][x].isEnemy()) {
						Iterator<Enemy> iterator = dungeon[y][x].getEnemies().iterator();
						iterator.next();
						iterator.remove();
						break;						
					}
				}
				break;
			case RIGHT:
				for(int i = 0; i < maxDist; i++) {
					x++;
					if(dungeon[y][x].getStructure() != null) {
						break;
					} else if(dungeon[y][x].isEnemy()) {
						Iterator<Enemy> iterator = dungeon[y][x].getEnemies().iterator();
						iterator.next();
						iterator.remove();
						break;						
					}
				}
				break;	
		}
		return false;
	}

}
