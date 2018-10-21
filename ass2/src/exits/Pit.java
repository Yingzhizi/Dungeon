package exits;

import ass2.Player;
import ass2.Status;

public class Pit extends Exits {

	public Pit() {
		this.name = "Pit";
	}

	
	@Override
	public boolean doAction(Player player) {
		if(!(player.getStatus() == Status.HOVER)) {
			player.die();
		}
		return false;
	}
}
