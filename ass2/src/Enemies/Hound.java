package Enemies;

public class Hound extends Enemy {
	
	public Hound(int xCoord, int yCoord) {		
		super("Hound", xCoord, yCoord, new HoundMove());
	}
}
