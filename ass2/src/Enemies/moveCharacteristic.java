package Enemies;

import ass2.Player;
import ass2.Square;

public interface moveCharacteristic {
	
	public void movement(Player p, Square[][] s, Enemy e);
	
	public default void move(Square[][] dungeon, int x, int y, Enemy e) {
	
		if(dungeon[y][x].getStructure() == null) {
			dungeon[e.getyCoord()][e.getxCoord()].killEnemy(e);
			dungeon[y][x].addEnemy(e);
			e.setXY(x, y);
		}
	}
	
	public default boolean checkCollision(Player p, int xCoord, int yCoord) {
		if(p.getX() == xCoord && p.getY() == yCoord) {
			return true;
		}
		return false;
	}
}
