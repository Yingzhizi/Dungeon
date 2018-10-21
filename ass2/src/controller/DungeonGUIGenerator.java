package controller;

import java.util.HashMap;

import Enemies.Coward;
import Enemies.Enemy;
import Enemies.Hound;
import Enemies.Hunter;
import Enemies.Strategist;
import ass2.Direction;
import ass2.DungeonSystem;
import collectables.Arrow;
import collectables.Bomb;
import collectables.FireMelee;
import collectables.Key;
import collectables.Sword;
import collectables.Treasure;
import consumables.Beer;
import consumables.HoverPotion;
import consumables.InvincibilityPotion;
import exits.Exit;
import exits.Pit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import structures.Door;
import structures.Wall;

public class DungeonGUIGenerator {
	
	private DungeonSystem dunSys;
	private GridPane dungeon;
	private HashMap<String, Image> preLoadImg;
	
	private Image ground;
	private Image wall;
	
	public DungeonGUIGenerator() {
		this.dunSys = dunSys;
		this.dungeon = dungeon;
		this.preLoadImg = new HashMap<String, Image>();
		
		preLoadImg.put("ground", new Image("assets/ground.png"));
		preLoadImg.put("wall", new Image("assets/wall.png"));
	}
	
	public StackPane buildStackPane(DungeonSystem dunSys, int i, int j) {
		StackPane s = new StackPane();
		if(dunSys.getSquare(i, j).getStructure() == null) {
			s.getChildren().add(makeGround());
			if(dunSys.getSquare(i, j).getEntity() != null) {
				if(dunSys.getSquare(i,j).getEntity() instanceof Arrow) {
					s.getChildren().add(makeArrow());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof Bomb) {
					s.getChildren().add(makeBombUnlit());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof Key) {
					s.getChildren().add(makeKey());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof Sword) {
					s.getChildren().add(makeSword());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof FireMelee) {
					s.getChildren().add(makeFireMelee()); 
				} else if(dunSys.getSquare(i,j).getEntity() instanceof Treasure) {
					s.getChildren().add(makeTreasure());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof HoverPotion) {
					s.getChildren().add(makeHoverPotion());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof InvincibilityPotion) {
					s.getChildren().add(makeInvincibilityPotion());
				} else if(dunSys.getSquare(i,j).getEntity() instanceof Exit) {
					s.getChildren().add(makeExit());
				} else if (dunSys.getSquare(i, j).getEntity() instanceof Pit) {
					s.getChildren().add(makePit());
				} else if (dunSys.getSquare(i, j).getEntity() instanceof Beer) {
					s.getChildren().add(makeBeer());
				}
			} else if (dunSys.getSquare(i,j).getBoulder() != null) {
				s.getChildren().add(makeBoulder());
			} else if (dunSys.getSquare(i,j).getSwitch() != null) {
				s.getChildren().add(makeSwitch());
			} 
			if(dunSys.getSquare(i, j).isEnemy()) {
				for(Enemy e : dunSys.getSquare(i, j).getEnemies()) {
					if(e instanceof Hunter) {
						s.getChildren().add(makeHunter());
					} else if(e instanceof Strategist) {
						s.getChildren().add(makeStrategist());
					} else if(e instanceof Hound) {
						s.getChildren().add(makeHound());
					} else if(e instanceof Coward) {
						s.getChildren().add(makeCoward());
					}
				}
			}
		} else {
			if(dunSys.getSquare(i, j).getStructure() instanceof Wall) {
				s.getChildren().add(makeWall());
			}  else if (dunSys.getSquare(i,j).getBoulder() != null) {
				s.getChildren().add(makeGround());
				s.getChildren().add(makeBoulder());
			} else if (dunSys.getSquare(i, j).getStructure() instanceof Door) {
				Door door = (Door) dunSys.getSquare(i, j).getStructure();
				s.getChildren().add(makeGround());
				if (!door.getState()) {
					s.getChildren().add(closeDoor());
				} else {
					s.getChildren().add(openDoor());
				}
			} else if (dunSys.getSquare(i,j).getBoulder() != null) {
				s.getChildren().add(makeBoulder());
			}
		}
		if(dunSys.getPlayer().getX() == j && dunSys.getPlayer().getY() == i) {
			if (dunSys.getPlayer().getDirection().equals(Direction.UP)) {
				s.getChildren().add(makePlayerBack());
			} else if (dunSys.getPlayer().getDirection().equals(Direction.DOWN)) {
				s.getChildren().add(makePlayer());
			} else if (dunSys.getPlayer().getDirection().equals(Direction.LEFT)) {
				s.getChildren().add(makePlayerLeft());
			} else if (dunSys.getPlayer().getDirection().equals(Direction.RIGHT)) {
				s.getChildren().add(makePlayerRight());
			}
		}
		return s;
	}
	
	//creates an ImageView based on file path given
	public ImageView makeImage(String path) {
		Image image = new Image(path);
		ImageView pic = new ImageView();
		pic.setFitHeight(40);
		pic.setFitWidth(40);
		pic.setImage(image);
		return pic;
	}
	
	// these set of functions create an image
	public ImageView makeGround() {
		ImageView pic = new ImageView();
		pic.setFitHeight(40);
		pic.setFitWidth(40);
		pic.setImage(preLoadImg.get("ground"));
		return pic;
	}

	public ImageView makeWall() {
		ImageView pic = new ImageView();
		pic.setFitHeight(40);
		pic.setFitWidth(40);
		pic.setImage(preLoadImg.get("wall"));
		return pic;
	}

	public ImageView makePlayer() {
		return makeImage("assets/player_stand.png");
	}

	public ImageView makePlayerLeft() {
		return makeImage("assets/player_left.png");
	}

	public ImageView makePlayerRight() {
		return makeImage("assets/player_right.png");
	}

	public ImageView makePlayerBack() {
		return makeImage("assets/player_back.png");
	}

	public ImageView makeArrow() {
		return makeImage("assets/arrow.png");
	}

	public ImageView makeKey() {
		return makeImage("assets/key.png");
	}

	public ImageView makeBombUnlit() {
		return makeImage("assets/bomb_unlit.png");
	}

	public ImageView makeSword() {
		return makeImage("assets/sword.png");
	}

	public ImageView makeFireMelee() {
		return makeImage("assets/fireSword.png");
	}
	
	public ImageView makeTreasure() {
		return makeImage("assets/treasure.png");
	}

	public ImageView makeHoverPotion() {
		return makeImage("assets/potion_hover.png");
	}


	public ImageView makeInvincibilityPotion() {
		return makeImage("assets/potion_invis.png");
	}

	public ImageView makeSwitch() {
		return makeImage("assets/switch.png");
	}

	public ImageView makeBoulder() {
		return makeImage("assets/boulder.png");
	}

	public ImageView makeExit() {
		return makeImage("assets/exit.png");
	}

	public ImageView makePit() {
		return makeImage("assets/pit.png");
	}

	public ImageView makeHunter() {
		return makeImage("assets/hunter.png");
	}

	public ImageView makeStrategist() {
		return makeImage("assets/strategist.png");

	}

	public ImageView makeHound() {
		return makeImage("assets/hound.png");
	}

	public ImageView makeCoward() {
		return makeImage("assets/coward.png");
	}

	public ImageView openDoor() {
		return makeImage("assets/door_open.png");
	}

	public ImageView closeDoor() {
		return makeImage("assets/door_closed.png");
	}

	public ImageView makeBeer() {
		return makeImage("assets/beer.png");
	}

}
