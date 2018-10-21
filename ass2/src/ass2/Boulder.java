package ass2;

import structures.Switch;

/**
 * Boulder class represent the boulder which has coordinate
 * has it own move function but basically controlled by the player
 */
public class Boulder {
	private String name;
	private int xCoord;
	private int yCoord;
	
	public Boulder(int x, int y) {
		this.name = "Boulder";
		this.xCoord = x;
		this.yCoord = y;
	}

	
	public String getName() {
		return name;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * the movement of the boulder
	 * @param x, the x coordinate the boulder ready to move
	 * @param y, the y coordinate the boulder ready to move
	 * @param dungeon, 2D array of dungeon map
	 * @return
	 */
	public boolean move(int x, int y, Square[][] dungeon) {
		
		// check the position want to move has boulder or not, if there has boulder, can't process
		if (dungeon[y][x].getBoulder() != null) {
			return false;
		}
		
		System.out.println(dungeon[y][x].getStructure());
		
		// else if the position want to move has block structure, can't process
		if(dungeon[y][x].getStructure() == null) {
			if (dungeon[y][x].getSwitch() != null) {
				activate(dungeon[y][x].getSwitch());
			}
			if (dungeon[yCoord][xCoord].getSwitch() != null){
				deactivate(dungeon, xCoord, yCoord);
			}
			this.xCoord = x;
			this.yCoord = y;
			return true;
		}
		
		return false;
	}
	
	/**
	 * activate a switch
	 * @param s, the switch need to been activated
	 * @return true/false
	 */
	public boolean activate(Switch s) {
		s.activateSwitch();
		return true;
	}
	
	/**
	 * deactivate the switch of the specific position
	 * @param dungeon, the dungeon map
	 * @param x, the x coordinate of the square want to check
	 * @param y, the y coordinate of the square want to check
	 */
	public void deactivate(Square[][] dungeon, int x, int y) {
		Switch s = dungeon[y][x].getSwitch();
		if (s != null) {
			s.deactivateSwitch();
		}
	}
	
}
