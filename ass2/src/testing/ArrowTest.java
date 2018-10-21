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
import collectables.Arrow;
import collectables.Sword;
import structures.Structure;
import structures.Wall;

public class ArrowTest {

	// attack no arrow
	@Test
	public void attackNoArrow() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Enemy e = new Hunter(1,2);
		map[1][2].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][2].isEnemy() == true);
		sys.getPlayer().attackArrow(map, sys.getPlayer(), eList);
		assert(map[1][2].isEnemy() == true);
	}
	
	
	// attack nothing
	@Test
	public void attackEmpty() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Arrow arrow = new Arrow();
		sys.getPlayer().getInventory().addItem(arrow);
		Enemy e = new Hunter(3,1);
		map[3][1].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[3][1].isEnemy() == true);
		sys.getPlayer().attackArrow(map, sys.getPlayer(), eList);
		assert(map[3][1].isEnemy() == true);
		assert(sys.getPlayer().getNumItem(arrow) == 0);
	}
	
	// change direction and shoot enemy
	@Test
	public void shootDirections() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Arrow arrow = new Arrow();
		sys.getPlayer().getInventory().addItem(arrow);
		Enemy e = new Hunter(5,1);
		map[5][1].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 7);
		
		assert(map[5][1].isEnemy() == true);
		sys.getPlayer().moveDown(map);
		sys.getPlayer().attackArrow(map, sys.getPlayer(), eList);
		assert(map[5][1].isEnemy() == false);
		assert(sys.getPlayer().getNumItem(arrow) == 0);
	}
	
	// attack enemy
	@Test
	public void attackEnemy() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Arrow arrow = new Arrow();
		sys.getPlayer().getInventory().addItem(arrow);
		Enemy e = new Hunter(1,3);
		map[1][3].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][3].isEnemy() == true);
		sys.getPlayer().attackArrow(map, sys.getPlayer(), eList);
		assert(map[1][3].isEnemy() == false);
		assert(sys.getPlayer().getNumItem(arrow) == 0);
	}
	
	// attack enemy but out of range so doesnt kill enemy
	@Test
	public void attackEnemyOutOfRange() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Arrow arrow = new Arrow();
		sys.getPlayer().getInventory().addItem(arrow);
		Enemy e = new Hunter(1,5);
		map[1][5].addEnemy(e);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][5].isEnemy() == true);
		sys.getPlayer().attackArrow(map, sys.getPlayer(), eList);
		assert(map[1][5].isEnemy() == true);
		assert(sys.getPlayer().getNumItem(arrow) == 0);
	}
	
	// there is a wall between player and enemy, arrow breaks on the wall
	@Test
	public void attackBlocked() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Arrow arrow = new Arrow();
		Structure wall = new Wall();
		sys.getPlayer().getInventory().addItem(arrow);
		Enemy e = new Hunter(1,5);
		map[1][5].addEnemy(e);
		map[1][4].addStructure(wall);
		ArrayList<Enemy> eList = new ArrayList<Enemy>();
		eList.add(e);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][5].isEnemy() == true);
		sys.getPlayer().attackArrow(map, sys.getPlayer(), eList);
		assert(map[1][5].isEnemy() == true);
		assert(sys.getPlayer().getNumItem(arrow) == 0);
	}
	
}
