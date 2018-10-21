package Enemies;

public class Coward extends Enemy {

	public Coward(int xCoord, int yCoord) {		
		super("Coward", xCoord, yCoord, new CowardMove());
	}

}
