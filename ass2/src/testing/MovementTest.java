package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ass2.DungeonSystem;
import ass2.Square;
import structures.*;

public class MovementTest {

	// test for walking onto empty squares, moving in all directions
	@Test
	public void moveAllDirections() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveDown(map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 2);
		sys.getPlayer().moveLeft(map);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 2);
		sys.getPlayer().moveUp(map);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
	}

	//test for walking into walls in all directions
	@Test
	public void moveBlockedByWall() {
		DungeonSystem sys = new DungeonSystem(); 	// srrounds the player with walls so cant move
		Square[][] map = sys.generateEmpty(3);
		Structure wall = new Wall();
		sys.addDungeon(map, 3);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveUp(map);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveDown(map);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveLeft(map);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
	}
	
	//test for walking into a locked door
	
	//test for walking into boulders that block the player (if there is
	//a wall/ another boulder behind the player
	
}
