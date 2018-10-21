package structures;

import ass2.Player;

public class Wall extends Structure {

	public Wall() {
		this.name = "Wall";
	}
	@Override
	public boolean isBlocked(Player player) {
		return true;
	}

}
