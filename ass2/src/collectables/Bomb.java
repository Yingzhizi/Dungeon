package collectables;

import ass2.Player;
import ass2.Square;

public class Bomb extends Collectable {

	public Bomb() {
		this.maxCapacity = 1;
		this.name = "Bomb";
	}
	
	@Override
	public boolean doAction(Player player) {
		if(player.getNumItem(this) < maxCapacity) {
			player.addItem(this);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * place the bomb in the inventory to the floor
	 * @param dungeon map
	 * @param p, player character
	 * @return
	 */
	public boolean placeBomb(Square[][] dungeon, Player p) {
		int x = p.getX();
		int y = p.getY();
		dungeon[y][x].addEntity(this);
		return true;
	}
}
