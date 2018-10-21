package collectables;

import ass2.Player;
import ass2.Square;

/**
 * key can be collected by player and has a specific id
 * key can also been used for open the door
 *
 */
public class Key extends Collectable {
	private static int id = 0;
	private int keyID;
	
	public Key() {
		this.maxCapacity = 1;
		this.name = "Key";
		keyID = id++;
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
	
	public int getKeyID() {
		return id;
	}
	
	/**
	 * place key hold by the player to the floor
	 * so that player can able to pick up another one
	 * @param dungeon map
	 * @param p, player character
	 * @return
	 */
	public boolean placeKey(Square[][] dungeon, Player p) {
		int x = p.getX();
		int y = p.getY();
		dungeon[y][x].addEntity(this);
		return true;
	}
	
	/**
	 * check if the key can unlock the door
	 * if the keyID match the doorID, open the door
	 * @param doorID
	 * @return true/false
	 */
	public boolean unlock(int doorID) {
		System.out.println("Door: " + doorID + " Key: " + keyID);
		if(this.keyID == doorID) {
			return true;
		}
		return false;
	}
	
}
