package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ass2.DungeonSystem;
import ass2.Entity;
import ass2.Square;
import collectables.*;

public class InventoryTest {

	// pick up one of each item from the ground
	@Test
	public void pickUpAll() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Entity sword = new Sword();
		Entity bomb = new Bomb();
		Entity key = new Key();
		Entity treasure = new Treasure();
		Entity arrow = new Arrow();
		
		map[1][2].addEntity(sword);
		map[1][3].addEntity(bomb);
		map[2][3].addEntity(key);
		map[2][2].addEntity(treasure);
		map[2][1].addEntity(arrow);
		
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getNumItem(sword) == 0);
		assert(sys.getPlayer().getNumItem(bomb) == 0);
		assert(sys.getPlayer().getNumItem(key) == 0);
		assert(sys.getPlayer().getNumItem(treasure) == 0);
		assert(sys.getPlayer().getNumItem(arrow) == 0);		
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(sword) == 1);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(bomb) == 1);
		sys.getPlayer().moveDown(map);
		assert(sys.getPlayer().getNumItem(key) == 1);
		sys.getPlayer().moveLeft(map);
		assert(sys.getPlayer().getNumItem(treasure) == 1);
		sys.getPlayer().moveLeft(map);
		assert(sys.getPlayer().getNumItem(arrow) == 1);
	}

	// pick up sword, but full
	@Test
	public void pickUpSwordFull() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Entity sword = new Sword();		
		map[1][2].addEntity(sword);
		map[1][3].addEntity(sword);
		
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getNumItem(sword) == 0);	
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(sword) == 1);
		assert(map[1][2].getEntity() == null);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(sword) == 1);
		assert(map[1][3].getEntity() instanceof Sword);
	}
	
	// pick up bomb, but full
	@Test
	public void pickUpBombFull() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Entity bomb = new Bomb();		
		map[1][2].addEntity(bomb);
		map[1][3].addEntity(bomb);
		
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getNumItem(bomb) == 0);	
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(bomb) == 1);
		assert(map[1][2].getEntity() == null);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(bomb) == 1);
		assert(map[1][3].getEntity() instanceof Bomb);
	}
	
	// pick up key, but full
	@Test
	public void pickUpKeyFull() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Entity key = new Key();		
		map[1][2].addEntity(key);
		map[1][3].addEntity(key);
		
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getNumItem(key) == 0);	
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(key) == 1);
		assert(map[1][2].getEntity() == null);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(key) == 1);
		assert(map[1][3].getEntity() instanceof Key);
	}
	
	// pick up many treasure
	@Test
	public void pickUpMayTreasure() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Entity treasure = new Treasure();		
		map[1][2].addEntity(treasure);
		map[1][3].addEntity(treasure);
		map[2][3].addEntity(treasure);
		
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getNumItem(treasure) == 0);	
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(treasure) == 1);
		assert(map[1][2].getEntity() == null);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(treasure) == 2);
		assert(map[1][3].getEntity() == null);
		sys.getPlayer().moveDown(map);
		assert(sys.getPlayer().getNumItem(treasure) == 3);
		assert(map[1][3].getEntity() == null);
	}
	
	// pick up many arrows
	@Test
	public void pickUpManyArrows() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Entity arrow = new Arrow();		
		map[1][2].addEntity(arrow);
		map[1][3].addEntity(arrow);
		map[2][3].addEntity(arrow);
		
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getNumItem(arrow) == 0);	
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(arrow) == 1);
		assert(map[1][2].getEntity() == null);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getNumItem(arrow) == 2);
		assert(map[1][3].getEntity() == null);
		sys.getPlayer().moveDown(map);
		assert(sys.getPlayer().getNumItem(arrow) == 3);
		assert(map[1][3].getEntity() == null);
	}
}
