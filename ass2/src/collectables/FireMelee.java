package collectables;

import ass2.Player;
import ass2.Square;

/**
 * A kind of weapon and used to attack enemy
 *
 */
public class FireMelee extends Collectable implements MeleeWeapon {

	private MeleeWeapon weapon;
	
	public FireMelee(MeleeWeapon weapon) {
		this.weapon = weapon;
	}
	
	public String getName() {
		return weapon.toString();
	}
	
	/**
	 * attack the enemies on all 4 squares around the player
	 */
	@Override
	public boolean attack(Square[][] dungeon, Player player) {
		attackSquare(dungeon[player.getY()-1][player.getX()]);
		attackSquare(dungeon[player.getY()+1][player.getX()]);
		attackSquare(dungeon[player.getY()][player.getX()-1]);
		attackSquare(dungeon[player.getY()][player.getX()+1]);
		return true;
	}


	@Override
	public boolean attackSquare(Square s) {
		return weapon.attackSquare(s);
	}

	@Override
	public boolean doAction(Player player) {
		if(player.getInventory().getSwordNum() < 1) {
			player.addItem(this);
			return true;
		} else {
			return false;
		}
	}


}
