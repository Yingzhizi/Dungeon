package structures;

import ass2.Player;

public class Door extends Structure {
	
	private static int ID = 0;
	private int doorID;
	
	private DoorState open;
	private DoorState closed;
	private DoorState currState;
	
	// false == closed, true == open
	public Door () {
		this.name = "Door";
		this.doorID = ID++;
		
		this.open = new OpenDoor(this);
		this.closed = new ClosedDoor(this);
		this.currState = closed;
	}
	
	public void unlockDoor() {
		currState = open;
	}
	
	@Override
	public boolean isBlocked(Player player) {
		return currState.isBlocked(player);
	}
	
	public int getID() {
		 return this.doorID;
	}
	
	public boolean getState() {
		if(currState.equals(open)) {
			return true;
		} 
		return false;
	}
	
}
