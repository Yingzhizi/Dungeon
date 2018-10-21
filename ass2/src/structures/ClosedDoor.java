package structures;

import ass2.Player;

public class ClosedDoor implements DoorState {

	private Door door;
	
	public ClosedDoor(Door door) {
		this.door = door;
	}
	
	
	@Override
	public boolean isBlocked(Player player) {
		if(player.unlock(door.getID())) {
			door.unlockDoor();
			return false;
		}
		return true;
	}

}
