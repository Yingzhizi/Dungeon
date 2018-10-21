package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ass2.DungeonSystem;
import ass2.Entity;
import ass2.Square;
import collectables.*;


import ass2.Boulder;


public class MoveBoulderTest {

	@Test
	public void moveRightAndStuck() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Boulder b = new Boulder(2, 1);
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		//player move to right
		sys.getPlayer().moveRight(map);
		b.movement(sys.getPlayer(), map);
		assert(b.getxCoord() == 3);
		assert(b.getyCoord() == 1);
		
		
		//stuck by wall, coordinate should be (3,1)
		sys.getPlayer().moveRight(map);
		b.movement(sys.getPlayer(), map);
		assert(b.getxCoord() == 3);
		assert(b.getyCoord() == 1);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);

	}
	
	@Test
	public void moveDownAndStuck() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Boulder b = new Boulder(2, 2);
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		//player move to right
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveDown(map);	
		b.movement(sys.getPlayer(), map);
		assert(b.getxCoord() == 2);
		assert(b.getyCoord() == 3);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 2);
		
		//stack by the wall
		sys.getPlayer().moveDown(map);	
		b.movement(sys.getPlayer(), map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 2);
	}
	
	@Test
	public void moveLeftAndStuck() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Boulder b = new Boulder(2, 2);
		sys.addDungeon(map, 5);
		sys.getPlayer().moveRight(map);
		sys.getPlayer().moveRight(map);
		sys.getPlayer().moveDown(map);
		sys.getPlayer().moveLeft(map);
		b.movement(sys.getPlayer(), map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 2);
		assert(b.getxCoord() == 1);
		assert(b.getyCoord() == 2);
	}
	
	@Test
	public void moveUpAndStuck() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Boulder b = new Boulder(2, 2);
		sys.addDungeon(map, 5);
		sys.getPlayer().moveDown(map);
		sys.getPlayer().moveDown(map);
		sys.getPlayer().moveRight(map);
		sys.getPlayer().moveUp(map);
		b.movement(sys.getPlayer(), map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 2);
		assert(b.getxCoord() == 2);
		assert(b.getyCoord() == 1);

		
		//move up again, stuck
		sys.getPlayer().moveUp(map);
		b.movement(sys.getPlayer(), map);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 2);
		assert(b.getxCoord() == 2);
		assert(b.getyCoord() == 1);
	}
	
}
