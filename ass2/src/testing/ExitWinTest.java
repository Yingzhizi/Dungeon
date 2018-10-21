package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import WinCondition.ExitWin;
import ass2.Boulder;
import ass2.DungeonSystem;
import ass2.Square;
import ass2.WinCondition;
import exits.Exit;
import structures.Structure;
import structures.Switch;

public class ExitWinTest {

	
	@Test
	public void reachExit() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		WinCondition w = new ExitWin(3, 3);
		
		//create a dungeon
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		//add win condition to the system
		sys.addWinCondition(w);
		
		//start moving the player in order to reach the exit
		sys.getPlayer().moveRight(map);
		assert(sys.checkWin() == false);
		
		sys.getPlayer().moveRight(map);
		assert(sys.checkWin() == false);
		
		sys.getPlayer().moveDown(map);
		assert(sys.checkWin() == false);
		
		sys.getPlayer().moveDown(map);
		assert(sys.checkWin() == true);
	}
	
	@Test
	//no win condition, no matter where player move, cannot finish the game
	public void noExit() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		//create a dungeon
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		//add win condition to the system
		
		//start moving the player in order to reach the exit
		sys.getPlayer().moveRight(map);
		assert(sys.checkWin() == false);
		
		sys.getPlayer().moveRight(map);
		assert(sys.checkWin() == false);
		
		sys.getPlayer().moveDown(map);
		assert(sys.checkWin() == false);
		
		sys.getPlayer().moveDown(map);
		assert(sys.checkWin() == false);
	}
	
	

}
