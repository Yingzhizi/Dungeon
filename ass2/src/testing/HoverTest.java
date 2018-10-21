package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ass2.DungeonSystem;
import ass2.Square;
import ass2.Status;
import consumables.HoverPotion;
import exits.Pit;

public class HoverTest {

	//pick up hover potion, player now has "HOVER" status
	@Test
	public void pickUpPotion() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		HoverPotion potion = new HoverPotion();
		map[1][2].addEntity(potion);
		sys.addDungeon(map, 5);
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getStatus() == Status.HOVER);
		assert(map[1][2].getEntity() == null);
	}
	
	//walking over pit while under effects of hover potion allows player to move on pit like a normal square
	@Test
	public void walkOverPit() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		Pit pit = new Pit();
		map[1][2].addEntity(pit);
		sys.addDungeon(map, 5);
		sys.getPlayer().setStatus(Status.HOVER);
		
		assert(sys.getPlayer().getLives() == 3);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getLives() == 3);
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getX() == 3);
		assert(sys.getPlayer().getY() == 1);
	}

	//when the player dies their status will be reset to "NONE"
	@Test
	public void looseEffectOnDeath() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		HoverPotion potion = new HoverPotion();
		map[1][2].addEntity(potion);
		sys.addDungeon(map, 5);
		
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getStatus() == Status.HOVER);
		sys.getPlayer().die();
		assert(sys.getPlayer().getStatus() == Status.NONE);
	}
}
