package Enemies;

import ass2.Player;
import ass2.Square;

public class CowardMove implements moveCharacteristic {
	

	@Override
	public void movement(Player p, Square[][] s, Enemy e) {
		int xDist, yDist, distFromP;
		int xCoord = e.getxCoord();
		int yCoord = e.getyCoord();
		xDist = xCoord - p.getX();
		yDist = yCoord - p.getY();
		distFromP = xDist*xDist + yDist*yDist;
		distFromP = (int) Math.sqrt(distFromP);
		if (distFromP <= 3) {
			if(yDist > xDist) {
				if(yDist >= 0) {
					move(s, xCoord, yCoord +1, e);
				} else if(yDist < 0) {
					move(s, xCoord, yCoord -1, e);
				} 
			} else if (xDist > yDist) {
				if(xDist > 0) {
					move(s, xCoord + 1, yCoord, e);
				} else if(xDist < 0) {
					move(s, xCoord - 1, yCoord, e);
				}
			} else if (xDist == 0 && yDist != 0) {
				int randomNumber = (int)(Math.random() % 2);
				if(randomNumber == 0) {
					if(xDist > 0) {
						move(s, xCoord + 1, yCoord, e);
					} else {
						move(s, xCoord - 1, yCoord, e);
					}
				} else {
					if(yDist > 0) {
						move(s, xCoord, yCoord + 1, e);
					} else {
						move(s, xCoord, yCoord - 1, e);
					}
				}
			} else if (yDist == 0 && xDist != 0) {
				int randomNumber = (int)(Math.random() % 2);
				if(randomNumber == 0) {
					if(yDist > 0) {
						move(s, xCoord, yCoord + 1, e);
					} else {
						move(s, xCoord, yCoord - 1,  e);
					}
				} else {
					if(xDist > 0) {
						move(s, xCoord + 1, yCoord, e);
					} else {
						move(s, xCoord - 1, yCoord, e);
					}
				}
			} else {
				int randomNumber = (int)(Math.random() % 2);
				if(randomNumber == 0) {
					if(xDist > 0) {
						move(s, xCoord + 1, yCoord,  e);
					} else {
						move(s, xCoord - 1, yCoord, e);
					}
				} else {
					if(yDist > 0) {
						move(s, xCoord, yCoord +1, e);
					} else {
						move(s, xCoord, yCoord -1, e);
					}
				}
			}
		} else if(yDist > xDist && (yDist != 0 && xDist != 0)) {
			if(yDist > 0) {
				move(s, xCoord, yCoord - 1, e);
			} else {
				move(s, xCoord, yCoord + 1, e);
			}
		} else if (xDist > yDist && (yDist != 0 && xDist != 0)){
			if(xDist > 0) {
				move(s, xCoord - 1, yCoord, e);
			} else {
				move(s, xCoord + 1, yCoord, e);
			}
		} else if(yDist == 0 && xDist != 0) {
			if(xDist > 0) {
				move(s, xCoord - 1, yCoord, e);
			} else {
				move(s, xCoord + 1, yCoord, e);
			}
		} else if(xDist == 0 && yDist != 0) {
			if(yDist > 0) {
				move(s, xCoord, yCoord - 1, e);
			} else {
				move(s, xCoord, yCoord + 1, e);
			}
		} else if(xDist == yDist && (xDist != 0 && yDist != 0)) {
			int randomNumber = (int)(Math.random() % 2);
			if(randomNumber == 0) {
				if(xDist > 0) {
					move(s, xCoord - 1, yCoord, e);
				} else {
					move(s, xCoord + 1, yCoord,e);
				}
			} else if (randomNumber == 1) {
				if(yDist > 0) {
					move(s, xCoord, yCoord - 1, e);
				} else {
					move(s, xCoord, yCoord + 1, e);
				}
			}
		}
		checkCollision(p, xCoord, yCoord);
	}
}
