package Enemies;

import java.util.Observable;

public class Hunter extends Enemy {
	
	public Hunter(int xCoord, int yCoord) {		
		super("Hunter", xCoord, yCoord, new HunterMove());
	}
}
