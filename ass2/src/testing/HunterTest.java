package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Enemies.Enemy;
import Enemies.Hunter;
import Enemies.HunterMove;
import ass2.*;

public class HunterTest {

	@Test
	public void placeOneHunter() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Enemy hunter = new Hunter(1, 3);
		map[1][3].addEnemy(hunter);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][3].isEnemy() == true);
		int xCoord = hunter.getxCoord();
		int yCoord = hunter.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 1);
	}
	
	@Test
	public void placeManyHunters() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy hunter1 = new Hunter(1, 3);
		Enemy hunter2 = new Hunter(2, 3);
		Enemy hunter3 = new Hunter(3, 5);
		Enemy hunter4 = new Hunter(6, 1);
		
		map[1][3].addEnemy(hunter1);
		map[2][3].addEnemy(hunter2);
		map[3][5].addEnemy(hunter3);
		map[6][1].addEnemy(hunter4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][3].isEnemy() == true);
		assert(map[2][3].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoordh1 = hunter1.getxCoord();
		int yCoordh1 = hunter1.getyCoord();
		int xCoordh2 = hunter2.getxCoord();
		int yCoordh2 = hunter2.getyCoord();
		int xCoordh3 = hunter3.getxCoord();
		int yCoordh3 = hunter3.getyCoord();
		int xCoordh4 = hunter4.getxCoord();
		int yCoordh4 = hunter4.getyCoord();
		
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
	public void checkOneHunterMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy hunter = new Hunter(1, 4);
		map[1][4].addEnemy(hunter);
		
		sys.addDungeon(map, 4);
		
		assert(map[1][4].isEnemy() == true);
		int xCoord = hunter.getxCoord();
		int yCoord = hunter.getyCoord();
		
		assert(xCoord == 4);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hunter.movement(sys.getPlayer(), map);
		
		xCoord = hunter.getxCoord();
		yCoord = hunter.getyCoord();
		
		assert(xCoord != 4);
		assert(yCoord == 1);
		
		sys.getPlayer().moveLeft(map);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		hunter.movement(sys.getPlayer(), map);
		
		xCoord = hunter.getxCoord();
		xCoord = hunter.getyCoord();
		
		assert(xCoord != 4 && xCoord != 3 && xCoord < 4);
		assert(yCoord == 1);
	}
	
	@Test
	public void checkManyHunterMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy hunter1 = new Hunter(1,4);
		Enemy hunter2 = new Hunter(2, 4);
		Enemy hunter3 = new Hunter(3, 5);
		Enemy hunter4 = new Hunter(6, 1);
		
		map[1][4].addEnemy(hunter1);
		map[2][4].addEnemy(hunter2);
		map[3][5].addEnemy(hunter3);
		map[6][1].addEnemy(hunter4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][4].isEnemy() == true);
		assert(map[2][4].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoordh1 = hunter1.getxCoord();
		int yCoordh1 = hunter1.getyCoord();
		int xCoordh2 = hunter2.getxCoord();
		int yCoordh2 = hunter2.getyCoord();
		int xCoordh3 = hunter3.getxCoord();
		int yCoordh3 = hunter3.getyCoord();
		int xCoordh4 = hunter4.getxCoord();
		int yCoordh4 = hunter4.getyCoord();
		
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
		
		hunter1.movement(sys.getPlayer(), map);
		hunter2.movement(sys.getPlayer(), map);
		hunter3.movement(sys.getPlayer(), map);
		hunter4.movement(sys.getPlayer(), map);
		
		xCoordh1 = hunter1.getxCoord();
		yCoordh1 = hunter1.getyCoord();
		xCoordh2 = hunter2.getxCoord();
		yCoordh2 = hunter2.getyCoord();
		xCoordh3 = hunter3.getxCoord();
		yCoordh3 = hunter3.getyCoord();
		xCoordh4 = hunter4.getxCoord();
		yCoordh4 = hunter4.getyCoord();
		
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
		
		Enemy hunter = new Hunter(2, 3);
		map[2][3].addEnemy(hunter);
		
		sys.addDungeon(map, 4);
		
		assert(map[2][3].isEnemy() == true);
		int xCoord = hunter.getxCoord();
		int yCoord = hunter.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 2);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hunter.movement(sys.getPlayer(), map);
		
		xCoord = hunter.getxCoord();
		yCoord = hunter.getyCoord();
		
		assert(xCoord != 3 || yCoord != 2);
	}
	
	@Test
	public void checkCollision() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy hunter = new Hunter(1, 5);
		map[1][5].addEnemy(hunter);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][5].isEnemy() == true);
		int xCoord = hunter.getxCoord();
		int yCoord = hunter.getyCoord();
		
		assert(xCoord == 5);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		hunter.movement(sys.getPlayer(), map);
		
		xCoord = hunter.getxCoord();
		yCoord = hunter.getyCoord();
		
		assert(xCoord != 5 && xCoord < 5);
		assert(yCoord == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 3);
		assert(sys.getPlayer().getY() == 1);
		
		hunter.movement(sys.getPlayer(), map);
		
		xCoord = hunter.getxCoord();
		xCoord = hunter.getyCoord();
		
		assert(xCoord != 5 && xCoord != 4 && xCoord < 5);
		assert(yCoord == 1);
		assert(hunter.checkCollision(sys.getPlayer()) == true);
	}

}
