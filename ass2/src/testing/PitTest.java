package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ass2.DungeonSystem;
import ass2.Square;
import ass2.Status;
import consumables.HoverPotion;
import exits.Pit;

public class PitTest {

	//falling to pit causes death and respawn
	@Test
	public void fallIntoPit() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		Pit p = new Pit();
		map[1][2].addEntity(p);
		sys.addDungeon(map, 5);
		
		assert(sys.getPlayer().getLives() == 3);
		sys.getPlayer().moveRight(map);
		assert(sys.getPlayer().getLives() == 2);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
	}
	
}
