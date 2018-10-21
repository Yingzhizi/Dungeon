package collectables;

import java.util.Iterator;

import Enemies.Enemy;
import ass2.Player;
import ass2.Square;

/**
 * 
 * sword is kind of weapon and collectable, can used for attack enemy
 * has durability of 5, after 5 attacks to enemy, sword broken
 *
 */
public class Sword extends Collectable implements MeleeWeapon {
	private int durability = 5;
	
	public Sword() {
		this.maxCapacity = 1;
		this.name = "Sword";
	}

	@Override
	public boolean doAction(Player player) {
		if(player.getNumItem(this) < maxCapacity) {
			player.addItem(this);
			return true;
		} else {
			return false;
		}
	}

	
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean attack(Square[][] dungeon, Player player) {
		switch(player.getDirection()) {
		case UP:
			System.out.println("Attack " + (player.getX())+ " " + (player.getY()-1) + " ");
			return attackSquare(dungeon[player.getY()-1][player.getX()]);
		case DOWN:
			System.out.println("Attack " + (player.getX())+ " " + (player.getY()+1) + " ");
			return attackSquare(dungeon[player.getY()+1][player.getX()]);
		case LEFT:
			System.out.println("Attack " + (player.getX()-1)+ " " + (player.getY()) + " ");
			return attackSquare(dungeon[player.getY()][player.getX()-1]);
		case RIGHT:
			System.out.println("Attack " + (player.getX()+1)+ " " + (player.getY()) + " ");
			return attackSquare(dungeon[player.getY()][player.getX()+1]);
		}
		return false;
	}
	
	/**
	 * attacks a given square and kills an enemy if present
	 * @param s
	 * @return true = sword has run out of durability and broken, false = not broken
	 */
	@Override
	public boolean attackSquare(Square s) {
		if(s.isEnemy()) {
			Iterator<Enemy> iterator = s.getEnemies().iterator();
			while(iterator.hasNext()) {
				Enemy e = iterator.next();
				iterator.remove();
				durability--;
				if(durability <= 0) {
					return true;
				}
			}
		}
		return false;
	}
	
}
