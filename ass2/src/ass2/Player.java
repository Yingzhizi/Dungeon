package ass2;

import exits.Pit;

/**
 * The player character which is controlled by the player
 *
 */

public class Player {
	/** x and y represent the index of the player's location in dungeon
	 * direction is the direction the player is currently facing
	 * inventory holds all the entities picked up by the player
	 */
	private int x;
	private int y;
	private Direction dir;
	private Inventory inventory;
	private Status status;
	private int lives;

	public Player() {
		x = 1;
		y = 1;
		dir = Direction.RIGHT;
		status = Status.NONE;
		inventory = new Inventory();
		lives = 3;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return this.dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void addItem(Entity item) {
		inventory.addItem(item);
	}

	public int getNumItem(Entity item) {
		return inventory.getNumItem(item);
	}

	public void printInventory() {
		inventory.printInventory();
	}


	public int getLives() {
		return lives;
	}

	public void die() {
		lives--;
		if(lives >= 0) {
			this.status = status.NONE;
			respawn();
		} else {

		}
	}

	public void gainLife() {
		if (lives > 0) {
			lives++;
		}
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setCreateCoord() {
		this.x = 2;
		this.y = 2;
	}

	public void respawn() {
		this.x = 1;
		this.y = 1;
	}


	/**
	 * move the player up, also check the square above has boulder or not
	 * if there has boulder, push the boulder
	 * @param dungeon
	 */
	public void moveUp(Square[][] dungeon) {
		move(x, y-1, dungeon[y-1][x]);
		setDirection(Direction.UP);
		if(dungeon[y][x].doAction(this)) {
			dungeon[y][x].removeEntity();
		}

		//move the boulder up
		Boulder b = dungeon[y][x].getBoulder();
		if (b != null) {
			if (!b.move(x, y - 1, dungeon)) {
				move(x, y+1, dungeon[y+1][x]);
			} else {
				dungeon[y][x].removeBoulder();
				if (!checkPit(dungeon, y-1, x)) {
					dungeon[y-1][x].addBoulder(b);
				}
			}
		}
	}

	public void moveDown(Square[][] dungeon) {
		move(x, y+1, dungeon[y+1][x]);
		setDirection(Direction.DOWN);
		if(dungeon[y][x].doAction(this)) {
			dungeon[y][x].removeEntity();
		}

		Boulder b = dungeon[y][x].getBoulder();
		if (b != null) {
			if (!b.move(x, y + 1, dungeon)) {
				move(x, y-1, dungeon[y-1][x]);
			} else {
				dungeon[y][x].removeBoulder();
				if (!checkPit(dungeon, y+1, x)) {
					dungeon[y+1][x].addBoulder(b);
				}
			}
		}

	}

	public void moveLeft(Square[][] dungeon) {
		move(x-1, y, dungeon[y][x-1]);
		setDirection(Direction.LEFT);
		if(dungeon[y][x].doAction(this)) {
			dungeon[y][x].removeEntity();
		}

		Boulder b = dungeon[y][x].getBoulder();
		if (b != null) {
			if (!b.move(x - 1, y, dungeon)) {
				move(x + 1, y, dungeon[y][x + 1]);
			} else {
				dungeon[y][x].removeBoulder();
				if (!checkPit(dungeon, y, x-1)) {
					dungeon[y][x-1].addBoulder(b);
				}
			}
		}

	}

	public void moveRight(Square[][] dungeon) {
		move(x+1, y, dungeon[y][x+1]);
		setDirection(Direction.RIGHT);
		if(dungeon[y][x].doAction(this)) {
			dungeon[y][x].removeEntity();
		}

		Boulder b = dungeon[y][x].getBoulder();
		if (b != null) {
			if (!b.move(x + 1, y, dungeon)) {
				move(x - 1, y, dungeon[y][x - 1]);
			} else {
				dungeon[y][x].removeBoulder();
				if (!checkPit(dungeon, y, x+1)) {
					dungeon[y][x+1].addBoulder(b);
				}
			}
		}
	}


	
	public boolean move(int x, int y, Square s) {
		if(!s.isBlocked(this)) {
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}

	public boolean checkPit(Square[][] dungeon, int x, int y) {
		if (dungeon[x][y].getEntity() instanceof Pit) {
			return true;
		}
		return false;
	}

	public void attackSword(Square[][] dungeon) {
		inventory.attackSword(dungeon, this);
	}

	public void attackArrow(Square[][] dungeon, Player player) {
		inventory.attackArrow(dungeon, this);
	}

	public void placeBomb(Square[][] dungeon, Player player) {
		inventory.attackBomb(dungeon, this);

	}


	public boolean unlock(int DoorID) {
		return inventory.unlock(DoorID);
	}

	public int getCollectTreasure() {
		return inventory.getTreasureNum();
	}

	public void placeKey(Square[][] dungeon, Player player) {
		inventory.DropKey(dungeon, this);

	}


}
