package ass2;

/**
 * Abstract class that encapsulates all entities that are interacted with
 * by the player walking on the square containing this entity
 * includes Consumables, Collectables, Exits
 */
public abstract class Entity {
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Entity e2) {
		return(e2.getName() == this.name);
	}
	
	/**
	 * The action to be done when the player walks on the square containing
	 * the entity
	 * @param palyer
	 * @return boolean if action could be complete
	 */
	public abstract boolean doAction(Player player);
}
