package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Enemies.Enemy;
import Enemies.Hound;
import Enemies.HoundMove;
import ass2.DungeonSystem;
import ass2.Square;

public class HoundTest {

	@Test
	public void placeOneHound() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Enemy hound = new Hound(1, 3);
		map[1][3].addEnemy(hound);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][3].isEnemy() == true);
		int xCoord = hound.getxCoord();
		int yCoord = hound.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 1);
	}
	
	@Test
	public void placeManyHounds() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy hound1 = new Hound(1, 3);
		Enemy hound2 = new Hound(2, 3);
		Enemy hound3 = new Hound(3, 5);
		Enemy hound4 = new Hound(6, 1);
		
		map[1][3].addEnemy(hound1);
		map[2][3].addEnemy(hound2);
		map[3][5].addEnemy(hound3);
		map[6][1].addEnemy(hound4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][3].isEnemy() == true);
		assert(map[2][3].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoordh1 = hound1.getxCoord();
		int yCoordh1 = hound1.getyCoord();
		int xCoordh2 = hound2.getxCoord();
		int yCoordh2 = hound2.getyCoord();
		int xCoordh3 = hound3.getxCoord();
		int yCoordh3 = hound3.getyCoord();
		int xCoordh4 = hound4.getxCoord();
		int yCoordh4 = hound4.getyCoord();
		
		assert(xCoordh1 == 3);
		assert(yCoordh1 == 1);
		assert(xCoordh2 == 3);
		assert(yCoordh2 == 2);
		assert(xCoordh3 == 5);
		assert(yCoordh3 == 3);
		assert(xCoordh4 == 1);
		assert(yCoordh4 == 6);
	}
	
	@Test
	public void checkOneHoundMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy hound = new Hound(1, 4);
		map[1][4].addEnemy(hound);
		
		sys.addDungeon(map, 4);
		
		assert(map[1][4].isEnemy() == true);
		int xCoord = hound.getxCoord();
		int yCoord = hound.getyCoord();
		
		assert(xCoord == 4);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hound.movement(sys.getPlayer(), map);
		
		xCoord = hound.getxCoord();
		yCoord = hound.getyCoord();
		
		assert(xCoord != 4);
		assert(yCoord == 1);
		
		sys.getPlayer().moveLeft(map);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		hound.movement(sys.getPlayer(), map);
		
		xCoord = hound.getxCoord();
		xCoord = hound.getyCoord();
		
		assert(xCoord != 4 && xCoord != 3 && xCoord < 4);
		assert(yCoord == 1);
	}
	
	@Test
	public void checkManyHoundMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy hound1 = new Hound(1,4);
		Enemy hound2 = new Hound(2, 4);
		Enemy hound3 = new Hound(3, 5);
		Enemy hound4 = new Hound(6, 1);
		
		map[1][4].addEnemy(hound1);
		map[2][4].addEnemy(hound2);
		map[3][5].addEnemy(hound3);
		map[6][1].addEnemy(hound4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][4].isEnemy() == true);
		assert(map[2][4].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoordh1 = hound1.getxCoord();
		int yCoordh1 = hound1.getyCoord();
		int xCoordh2 = hound2.getxCoord();
		int yCoordh2 = hound2.getyCoord();
		int xCoordh3 = hound3.getxCoord();
		int yCoordh3 = hound3.getyCoord();
		int xCoordh4 = hound4.getxCoord();
		int yCoordh4 = hound4.getyCoord();
		
		assert(xCoordh1 == 4);
		assert(yCoordh1 == 1);
		assert(xCoordh2 == 4);
		assert(yCoordh2 == 2);
		assert(xCoordh3 == 5);
		assert(yCoordh3 == 3);
		assert(xCoordh4 == 1);
		assert(yCoordh4 == 6);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hound1.movement(sys.getPlayer(), map);
		hound2.movement(sys.getPlayer(), map);
		hound3.movement(sys.getPlayer(), map);
		hound4.movement(sys.getPlayer(), map);
		
		xCoordh1 = hound1.getxCoord();
		yCoordh1 = hound1.getyCoord();
		xCoordh2 = hound2.getxCoord();
		yCoordh2 = hound2.getyCoord();
		xCoordh3 = hound3.getxCoord();
		yCoordh3 = hound3.getyCoord();
		xCoordh4 = hound4.getxCoord();
		yCoordh4 = hound4.getyCoord();
		
		assert(xCoordh1 != 4);
		assert(yCoordh1 == 1);
		assert(xCoordh2 != 4);
		assert(yCoordh2 == 2);
		assert(xCoordh3 != 5);
		assert(yCoordh3 == 3);
		assert(xCoordh4 == 1);
		assert(yCoordh4 != 6);
	}
	
	@Test
	public void checkMoveSameXAndYDist() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy hound = new Hound(2, 3);
		map[2][3].addEnemy(hound);
		
		sys.addDungeon(map, 4);
		
		assert(map[2][3].isEnemy() == true);
		int xCoord = hound.getxCoord();
		int yCoord = hound.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 2);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hound.movement(sys.getPlayer(), map);
		
		xCoord = hound.getxCoord();
		yCoord = hound.getyCoord();
		
		assert(xCoord != 3 || yCoord != 2);
	}
	
	@Test
	public void checkCollision() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy hound = new Hound(1, 5);
		map[1][5].addEnemy(hound);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][5].isEnemy() == true);
		int xCoord = hound.getxCoord();
		int yCoord = hound.getyCoord();
		
		assert(xCoord == 5);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hound.movement(sys.getPlayer(), map);
		
		xCoord = hound.getxCoord();
		yCoord = hound.getyCoord();
		
		assert(xCoord != 5 && xCoord < 5);
		assert(yCoord == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 3);
		assert(sys.getPlayer().getY() == 1);
		
		hound.movement(sys.getPlayer(), map);
		
		xCoord = hound.getxCoord();
		xCoord = hound.getyCoord();
		
		assert(xCoord != 5 && xCoord != 4 && xCoord < 5);
		assert(yCoord == 1);
		assert(hound.checkCollision(sys.getPlayer()) == true);
	}

}
