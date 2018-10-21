package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Enemies.Enemy;
import Enemies.Strategist;
import Enemies.StrategistMove;
import ass2.DungeonSystem;
import ass2.Square;

public class StrategistTest {

	@Test
	public void placeOneStrategist() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Enemy strategist = new Strategist(1, 3);
		map[1][3].addEnemy(strategist);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][3].isEnemy() == true);
		int xCoord = strategist.getxCoord();
		int yCoord = strategist.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 1);
	}
	
	@Test
	public void placeManyStrategists() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy strategist1 = new Strategist(1, 3);
		Enemy strategist2 = new Strategist(2, 3);
		Enemy strategist3 = new Strategist(3, 5);
		Enemy strategist4 = new Strategist(6, 1);
		
		map[1][3].addEnemy(strategist1);
		map[2][3].addEnemy(strategist2);
		map[3][5].addEnemy(strategist3);
		map[6][1].addEnemy(strategist4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][3].isEnemy() == true);
		assert(map[2][3].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoords1 = strategist1.getxCoord();
		int yCoords1 = strategist1.getyCoord();
		int xCoords2 = strategist2.getxCoord();
		int yCoords2 = strategist2.getyCoord();
		int xCoords3 = strategist3.getxCoord();
		int yCoords3 = strategist3.getyCoord();
		int xCoords4 = strategist4.getxCoord();
		int yCoords4 = strategist4.getyCoord();
		
		assert(xCoords1 == 3);
		assert(yCoords1 == 1);
		assert(xCoords2 == 3);
		assert(yCoords2 == 2);
		assert(xCoords3 == 5);
		assert(yCoords3 == 3);
		assert(xCoords4 == 1);
		assert(yCoords4 == 6);
	}
	
	@Test
	public void checkOneStrategistMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy strategist = new Strategist(1, 4);
		map[1][4].addEnemy(strategist);
		
		sys.addDungeon(map, 4);
		
		assert(map[1][4].isEnemy() == true);
		int xCoord = strategist.getxCoord();
		int yCoord = strategist.getyCoord();
		
		assert(xCoord == 4);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		strategist.movement(sys.getPlayer(), map);
		
		xCoord = strategist.getxCoord();
		yCoord = strategist.getyCoord();
		
		assert(xCoord != 4);
		assert(yCoord == 1);
		
		sys.getPlayer().moveLeft(map);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		strategist.movement(sys.getPlayer(), map);
		
		xCoord = strategist.getxCoord();
		xCoord = strategist.getyCoord();
		
		assert(xCoord != 4 && xCoord != 3 && xCoord < 4);
		assert(yCoord == 1);
	}
	
	@Test
	public void checkManyStrategistMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy strategist1 = new Strategist(1,4);
		Enemy strategist2 = new Strategist(2, 4);
		Enemy strategist3 = new Strategist(3, 5);
		Enemy strategist4 = new Strategist(6, 1);
		
		map[1][4].addEnemy(strategist1);
		map[2][4].addEnemy(strategist2);
		map[3][5].addEnemy(strategist3);
		map[6][1].addEnemy(strategist4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][4].isEnemy() == true);
		assert(map[2][4].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoords1 = strategist1.getxCoord();
		int yCoords1 = strategist1.getyCoord();
		int xCoords2 = strategist2.getxCoord();
		int yCoords2 = strategist2.getyCoord();
		int xCoords3 = strategist3.getxCoord();
		int yCoords3 = strategist3.getyCoord();
		int xCoords4 = strategist4.getxCoord();
		int yCoords4 = strategist4.getyCoord();
		
		assert(xCoords1 == 4);
		assert(yCoords1 == 1);
		assert(xCoords2 == 4);
		assert(yCoords2 == 2);
		assert(xCoords3 == 5);
		assert(yCoords3 == 3);
		assert(xCoords4 == 1);
		assert(yCoords4 == 6);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		strategist1.movement(sys.getPlayer(), map);
		strategist2.movement(sys.getPlayer(), map);
		strategist3.movement(sys.getPlayer(), map);
		strategist4.movement(sys.getPlayer(), map);
		
		xCoords1 = strategist1.getxCoord();
		yCoords1 = strategist1.getyCoord();
		xCoords2 = strategist2.getxCoord();
		yCoords2 = strategist2.getyCoord();
		xCoords3 = strategist3.getxCoord();
		yCoords3 = strategist3.getyCoord();
		xCoords4 = strategist4.getxCoord();
		yCoords4 = strategist4.getyCoord();
		
		assert(xCoords1 != 4);
		assert(yCoords1 == 1);
		assert(xCoords2 != 4);
		assert(yCoords2 == 2);
		assert(xCoords3 != 5);
		assert(yCoords3 == 3);
		assert(xCoords4 == 1);
		assert(yCoords4 != 6);
	}
	
	@Test
	public void checkMoveSameXAndYDist() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy strategist = new Strategist(2, 3);
		map[2][3].addEnemy(strategist);
		
		sys.addDungeon(map, 4);
		
		assert(map[2][3].isEnemy() == true);
		int xCoord = strategist.getxCoord();
		int yCoord = strategist.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 2);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		strategist.movement(sys.getPlayer(), map);
		
		xCoord = strategist.getxCoord();
		yCoord = strategist.getyCoord();
		
		assert(xCoord != 3 || yCoord != 2);
	}
	
	@Test
	public void checkCollision() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy strategist = new Strategist(1, 5);
		map[1][5].addEnemy(strategist);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][5].isEnemy() == true);
		int xCoord = strategist.getxCoord();
		int yCoord = strategist.getyCoord();
		
		assert(xCoord == 5);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		strategist.movement(sys.getPlayer(), map);
		
		xCoord = strategist.getxCoord();
		yCoord = strategist.getyCoord();
		
		assert(xCoord != 5 && xCoord < 5);
		assert(yCoord == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 3);
		assert(sys.getPlayer().getY() == 1);
		
		strategist.movement(sys.getPlayer(), map);
		
		xCoord = strategist.getxCoord();
		xCoord = strategist.getyCoord();
		
		assert(xCoord != 5 && xCoord != 4 && xCoord < 5);
		assert(yCoord == 1);
		assert(strategist.checkCollision(sys.getPlayer()) == true);
	}

}
