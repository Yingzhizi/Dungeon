package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Enemies.Enemy;
import Enemies.Hunter;
import Enemies.HunterMove;
import ass2.DungeonSystem;
import ass2.Entity;
import ass2.Square;
import collectables.Sword;

public class SwordTest {

	// attack no sword
	@Test
	public void attackNoSword() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Enemy e = new Hunter(1, 2);
		map[1][2].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][2].isEnemy() == true);
		sys.getPlayer().attackSword(map, eList);
		assert(map[1][2].isEnemy() == true);
	}
	
	
	// attack nothing
	@Test
	public void attackEmpty() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Sword sword = new Sword();
		sys.getPlayer().getInventory().addItem(sword);
		Enemy e = new Hunter(1, 3);
		map[1][3].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][3].isEnemy() == true);
		sys.getPlayer().attackSword(map, eList);
		assert(map[1][3].isEnemy() == true);
		assert(sword.getDurability() == 5);
	}
	
	// attack enemy
	@Test
	public void attackEnemy() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Sword sword = new Sword();
		sys.getPlayer().getInventory().addItem(sword);
		Enemy e = new Hunter(1, 2);
		map[1][2].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][2].isEnemy() == true);
		sys.getPlayer().attackSword(map, eList);
		assert(map[1][2].isEnemy() == false);
		assert(sword.getDurability() == 4);
	}
	
	
	// attack enemy, sword breaks
	@Test
	public void attackEnemyBreakSword() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Sword sword = new Sword();
		((Sword)sword).setDurability(1);
		sys.getPlayer().getInventory().addItem(sword);
		Enemy e = new Hunter(1, 2);
		map[1][2].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][2].isEnemy() == true);
		sys.getPlayer().attackSword(map, eList);
		assert(map[1][2].isEnemy() == false);
		assert(sword.getDurability() == 0);
		assert(sys.getPlayer().getInventory().getNumItem(sword) == 0);
	}
}
