package collectables;

import ass2.Player;
import ass2.Square;

public interface MeleeWeapon {
	public boolean attack(Square[][] dungeon, Player player);
	public boolean attackSquare(Square s);
	public boolean doAction(Player player);
}
