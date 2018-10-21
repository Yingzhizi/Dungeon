package Enemies;

public class Strategist extends Enemy {

	public Strategist(int xCoord, int yCoord) {		
		super("Strategist", xCoord, yCoord, new StrategistMove());
	}
}
