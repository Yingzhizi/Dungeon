 package Enemies;

import java.util.Observable;
import java.util.Observer;

import ass2.Dungeon;
import ass2.DungeonSystem;
import ass2.Player;
import ass2.Square;

public abstract class Enemy implements Observer {
	
	private moveCharacteristic moveMethod;
	protected String name;
	protected int xCoord;
	protected int yCoord;
	
	public Enemy(String name, int yCoord, int xCoord, moveCharacteristic m) {
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.moveMethod = m;
	}
	
	public moveCharacteristic getMoveMethod() {
		return moveMethod;
	}

	public void setMoveMethod(moveCharacteristic moveMethod) {
		this.moveMethod = moveMethod;
	}

	public String getName() {
		return this.name;
	}
	
	public boolean equals(Enemy ec) {
		return(ec.getName() == this.name);
	}
	
	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}
	
	public void setXY(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}
	
	
	public boolean checkCollision(Player p) {
		return moveMethod.checkCollision(p, xCoord, yCoord);
	}
	
	
	public void movement(Player p, Square[][] s) {
		moveMethod.movement(p, s, this);
	}
	
	
	// i know its a huge mess, will fix this.
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(!((Square[][])arg1)[yCoord][xCoord].getEnemies().contains(this)) {
			((DungeonSystem) arg0).deleteObserver(this);
		} else {
			movement((((DungeonSystem) arg0).getPlayer()), ((Square[][])arg1));
		}
		if(checkCollision(((DungeonSystem) arg0).getPlayer())) {
			((DungeonSystem) arg0).getPlayer().die();
		}
	}
}
