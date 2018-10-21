package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import WinCondition.SwitchWin;
import ass2.Boulder;
import ass2.DungeonSystem;
import ass2.Square;
import ass2.WinCondition;
import structures.Structure;
import structures.Switch;

public class SwitchesWinTest {

	@Test
	public void oneSwitchWin() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		//create boulder and add it to map
		Boulder b = new Boulder(2, 1);
		//check if add to the map successfully
		
		//create switch and add it to map
		Structure s = new Switch(3, 1);
		map[3][1].addStructure(s);
		assert(map[3][1].isSwitch() == true);
		
		//create a dungeon
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		//add switch to the dungeon
		sys.addSwitches(s);
		
		//create win condition, add it to system
		WinCondition switchesWin = new SwitchWin();
		sys.addWinCondition(switchesWin);
		
		//player move to right, push the boulder to the top of the switch
		sys.getPlayer().moveRight(map);
		b.movement(sys.getPlayer(), map);
		
		//because the win condition of this dungeon is activate all switches
		//this dungeon only has one switch and been activated already
		//checkWin should be true
		assert(sys.checkWin() == true);
	}
	
	@Test
	public void twoSwitchWin() {
		DungeonSystem sys = new DungeonSystem();
		Square[][] map = sys.generateEmpty(5);
		
		//create boulder and add it to map
		Boulder b1 = new Boulder(2, 1);

		
		//create another boulder and add it to map
		Boulder b2 = new Boulder(1, 2);

		
		//create switch and add it to map
		Structure s1 = new Switch(3, 1);
		map[3][1].addStructure(s1);
		assert(map[3][1].isSwitch() == true);
		
		//create switch and add it to map
		Structure s2 = new Switch(1, 3);
		map[1][3].addStructure(s2);
		assert(map[1][3].isSwitch() == true);
		
		//create a dungeon
		sys.addDungeon(map, 5);
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 1);
		//add switches to the dungeon
		sys.addSwitches(s1);
		sys.addSwitches(s2);
		
		//create win condition, add it to system
		WinCondition switchesWin = new SwitchWin();
		sys.addWinCondition(switchesWin);
		
		//player move to right, push the boulder to the top of the switch
		sys.getPlayer().moveRight(map);
		b1.movement(sys.getPlayer(), map);
		
		//because the win condition of this dungeon is activate all switches
		//this dungeon only has one activated switch, doean't win yet
		assert(sys.checkWin() == false);
		
		//player move left, then move down to push another boulder to the top of switch s2
		sys.getPlayer().moveLeft(map);
		sys.getPlayer().moveDown(map);
		//check if the player in the correct position
		assert(sys.getPlayer().getX() == 1);
		assert(sys.getPlayer().getY() == 2);
		b2.movement(sys.getPlayer(), map);
		//check if all the switches been activated
		assert(sys.checkWin() == true);	
	}
	
	

}
