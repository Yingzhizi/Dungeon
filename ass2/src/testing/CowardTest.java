package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Enemies.Coward;
import Enemies.Enemy;
import ass2.DungeonSystem;
import ass2.Square;

public class CowardTest {

	@Test
	public void placeOneCoward() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		Enemy coward = new Coward(1, 3);
		map[1][3].addEnemy(coward);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][3].isEnemy() == true);
		int xCoord = coward.getxCoord();
		int yCoord = coward.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 1);
	}
	
	@Test
	public void placeManyCowards() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy coward1 = new Coward(1, 3);
		Enemy coward2 = new Coward(2, 3);
		Enemy coward3 = new Coward(3, 5);
		Enemy coward4 = new Coward(6, 1);
		
		map[1][3].addEnemy(coward1);
		map[2][3].addEnemy(coward2);
		map[3][5].addEnemy(coward3);
		map[6][1].addEnemy(coward4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][3].isEnemy() == true);
		assert(map[2][3].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoordc1 = coward1.getxCoord();
		int yCoordc1 = coward1.getyCoord();
		int xCoordc2 = coward2.getxCoord();
		int yCoordc2 = coward2.getyCoord();
		int xCoordc3 = coward3.getxCoord();
		int yCoordc3 = coward3.getyCoord();
		int xCoordc4 = coward4.getxCoord();
		int yCoordc4 = coward4.getyCoord();
		
		assert(xCoordc1 == 3);
		assert(yCoordc1 == 1);
		assert(xCoordc2 == 3);
		assert(yCoordc2 == 2);
		assert(xCoordc3 == 5);
		assert(yCoordc3 == 3);
		assert(xCoordc4 == 1);
		assert(yCoordc4 == 6);
	}
	
	@Test
	public void checkOneCowardMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy coward = new Coward(1, 4);
		map[1][4].addEnemy(coward);
		
		sys.addDungeon(map, 4);
		
		assert(map[1][4].isEnemy() == true);
		int xCoord = coward.getxCoord();
		int yCoord = coward.getyCoord();
		
		assert(xCoord == 4);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		coward.movement(sys.getPlayer(), map);
		
		xCoord = coward.getxCoord();
		yCoord = coward.getyCoord();
		
		assert(xCoord != 4 || yCoord == 1);
		
		sys.getPlayer().moveLeft(map);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		coward.movement(sys.getPlayer(), map);
		
		xCoord = coward.getxCoord();
		xCoord = coward.getyCoord();
		
		assert(xCoord != 4 || yCoord != 1);
	}
	
	@Test
	public void checkManyCowardMove() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy coward1 = new Coward(1,4);
		Enemy coward2 = new Coward(2, 4);
		Enemy coward3 = new Coward(3, 5);
		Enemy coward4 = new Coward(6, 1);
		
		map[1][4].addEnemy(coward1);
		map[2][4].addEnemy(coward2);
		map[3][5].addEnemy(coward3);
		map[6][1].addEnemy(coward4);
		
		sys.addDungeon(map, 7);
		
		assert(map[1][4].isEnemy() == true);
		assert(map[2][4].isEnemy() == true);
		assert(map[3][5].isEnemy() == true);
		assert(map[6][1].isEnemy() == true);
		
		int xCoordc1 = coward1.getxCoord();
		int yCoordc1 = coward1.getyCoord();
		int xCoordc2 = coward2.getxCoord();
		int yCoordc2 = coward2.getyCoord();
		int xCoordc3 = coward3.getxCoord();
		int yCoordc3 = coward3.getyCoord();
		int xCoordc4 = coward4.getxCoord();
		int yCoordc4 = coward4.getyCoord();
		
		assert(xCoordc1 == 4);
		assert(yCoordc1 == 1);
		assert(xCoordc2 == 4);
		assert(yCoordc2 == 2);
		assert(xCoordc3 == 5);
		assert(yCoordc3 == 3);
		assert(xCoordc4 == 1);
		assert(yCoordc4 == 6);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		coward1.movement(sys.getPlayer(), map);
		coward2.movement(sys.getPlayer(), map);
		coward3.movement(sys.getPlayer(), map);
		coward4.movement(sys.getPlayer(), map);
		
		xCoordc1 = coward1.getxCoord();
		yCoordc1 = coward1.getyCoord();
		xCoordc2 = coward2.getxCoord();
		yCoordc2 = coward2.getyCoord();
		xCoordc3 = coward3.getxCoord();
		yCoordc3 = coward3.getyCoord();
		xCoordc4 = coward4.getxCoord();
		yCoordc4 = coward4.getyCoord();
		
		assert(xCoordc1 != 4 || yCoordc1 != 1);
		assert(xCoordc2 != 4 || yCoordc2 != 2);
		assert(xCoordc3 != 5 || yCoordc3 == 3);
		assert(xCoordc4 != 1 || yCoordc4 != 6);
	}
	
	@Test
	public void checkMoveSameXAndYDist() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(6);
		
		Enemy coward = new Coward(2, 3);
		map[2][3].addEnemy(coward);
		
		sys.addDungeon(map, 4);
		
		assert(map[2][3].isEnemy() == true);
		int xCoord = coward.getxCoord();
		int yCoord = coward.getyCoord();
		
		assert(xCoord == 3);
		assert(yCoord == 2);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		coward.movement(sys.getPlayer(), map);
		
		xCoord = coward.getxCoord();
		yCoord = coward.getyCoord();
		
		assert(xCoord != 3 || yCoord != 2);
	}
	
	@Test
	public void checkCollision() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(7);
		
		Enemy coward = new Coward(1, 5);
		map[1][5].addEnemy(coward);
		
		sys.addDungeon(map, 5);
		
		assert(map[1][5].isEnemy() == true);
		int xCoord = coward.getxCoord();
		int yCoord = coward.getyCoord();
		
		assert(xCoord == 5);
		assert(yCoord == 1);
		
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 2);
		assert(sys.getPlayer().getY() == 1);
		
		coward.movement(sys.getPlayer(), map);
		
		xCoord = coward.getxCoord();
		yCoord = coward.getyCoord();
		
		assert(xCoord == 5);
		assert(yCoord == 1);
		
		sys.getPlayer().moveRight(map);
		
		assert(sys.getPlayer().getX() == 3);
		assert(sys.getPlayer().getY() == 1);
		
		coward.movement(sys.getPlayer(), map);
		
		xCoord = coward.getxCoord();
		xCoord = coward.getyCoord();
		
		assert(xCoord != 4);
		assert(yCoord == 1);
		assert(coward.checkCollision(sys.getPlayer()) == false);
	}
}
