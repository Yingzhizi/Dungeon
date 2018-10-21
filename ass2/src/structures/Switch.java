package structures;


public class Switch {
	
	private int switchXCoord;
	private int switchYCoord;
	private boolean activate;
	
	public Switch (int x, int y) {
		this.switchXCoord = x;
		this.switchYCoord = y;
		this.activate = false;
	}
	
	
	public int getX() {
		return this.switchXCoord;
	}
	
	public int getY() {
		return this.switchYCoord;
	}
	
	public void activateSwitch() {
		this.activate = true;
	}
	
	public void deactivateSwitch() {
		this.activate = false;
	}


	public boolean open() {
		return this.activate;
	}
	
}
