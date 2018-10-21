package structures;

import ass2.Player;

public class OpenDoor implements DoorState {

	private Door door;
	
	public OpenDoor(Door door) {
		this.door = door;
	}
	
	@Override
	public boolean isBlocked(Player player) {
		return false;
	}

}
