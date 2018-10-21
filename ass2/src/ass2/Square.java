package ass2;

import java.util.ArrayList;

import Enemies.Enemy;
import structures.Structure;
import structures.Switch;

public class Square {
	
	/*
	 * not sure if entities and structures shold be arraylist or just a single
	 * object. 
	 * there should only be 1 collectable/item or potion or pit or exit on a single square
	 * same as pit, boulder and wall
	 * 
	 * can change to arraylists if needed
	 */
	
	private Entity entity;
	private Structure structure; 
	private ArrayList<Enemy> enemies;
	private Boulder boulder;
	private Switch swi;
	
	public Square() {
		this.entity = null;
		this.structure = null;
		this.enemies = new ArrayList<Enemy>();
		this.boulder = null;
		this.swi = null;
	}
	
	public void addEntity(Entity e) {
		entity = e;
	}
	
	public void removeEntity() {
		entity = null;
	}
	
	public void addStructure(Structure s) {
		structure = s;
	}
	
	public void removeStructure() {
		structure = null;
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}
	
	
	public void killEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	public void addBoulder(Boulder b) {
		boulder = b;
	}

	public void removeBoulder() {
		boulder = null;
	}
	
	public void addSwitch(Switch s) {
		swi = s;
	}
	
	public void removeSwitch() {
		swi = null;
	}
	
	public Boulder getBoulder() {
		return boulder;
	}
	
	public Switch getSwitch() {
		return swi;
	}
	
	public boolean isBlocked(Player player) {
		if(structure == null) return false;
		return structure.isBlocked(player);
	}
	
	public boolean doAction(Player player) {
		if(entity == null) return false;
		return entity.doAction(player);
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public Structure getStructure() {
		return structure;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	
	public boolean isEnemy() {
		if (enemies.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	//for testing
	/**
	 * show simple simple in the square represent for each entity
	 * @return
	 */
	public boolean isTreasure() {
		if (entity != null && entity.getName().equals("Treasure")) {
			return true;
		}
		return false;
	}
	
	public boolean isSwitch() {
		if (structure != null && structure.getName().equals("Switch")) {
			return true;
		}
		return false;
	}
	
	
	public boolean isWall() {
		if(structure != null && structure.getName().equals("Wall")) {
			return true;
		}
		return false;
	}
	
	public boolean isPit() {
		if(entity != null && entity.getName().equals("Pit")) {
			return true;
		}
		return false;
	}
	
	public boolean isBomb() {
		if(entity != null && entity.getName().equals("Bomb")) {
			return true;
		}
		return false;
	}
}
