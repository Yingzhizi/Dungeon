package controller;

import Enemies.Coward;
import Enemies.Enemy;
import Enemies.Hound;
import Enemies.Hunter;
import Enemies.Strategist;
import ass2.CreatorSystem;
import ass2.Direction;
import ass2.DungeonSystem;
import collectables.Arrow;
import collectables.Bomb;
import collectables.Key;
import collectables.Sword;
import collectables.Treasure;
import consumables.Beer;
import consumables.HoverPotion;
import consumables.InvincibilityPotion;
import exits.Exit;
import exits.Pit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import structures.Door;
import structures.Switch;
import structures.Wall;
import view.WinScreen;
import view.gameOver;

public class CreateGame {

	ObservableList<String> itemL = FXCollections.observableArrayList();

	private Stage currstage;
	private CreatorSystem dunSys;
	private int dungeonSize;

    @FXML
    private Button Start;

    @FXML
    private TextField yCoord;

    @FXML
    private TextField xCoord;


    @FXML
    private ChoiceBox<String> itemList;

    @FXML
    private GridPane dungeon;
    
	@FXML
	private Label item1;
	@FXML
	private Label item2;
	@FXML
	private Label item3;
	@FXML
	private Label item4;
	@FXML
	private Label item5;
	@FXML
	private Label numLives;
//	@FXML
//	private Label objectives;

	public CreateGame(Stage s, int size) {
		this.currstage = s;
		this.dungeonSize = size;
		this.dunSys = new CreatorSystem(size);
		//now there has no file yet???
		dunSys.createMapFile();
	}

	private void addItemToList() {
		itemL.removeAll(itemL);
		String wall = "Structure wall";
		String exit = "Entity exit";
		String treasure = "Entity treasure";
		String door = "Structure door";
		String key = "Entity key";
		String boulder = "Boulder boulder";
		String sw = "Switch switch";
		String bomb = "Entity bomb";
		String pit = "Entity pit";
		String hunter = "Enemy hunter";
		String strat = "Enemy strategist";
		String hound = "Enemy hound";
		String coward = "Enemy coward";
		String sword = "Entity sword";
		String arrow = "Entity arrow";
		String ip = "Entity ipotion";
		String hp = "Entity hpotion";

		itemL.addAll(wall, exit, treasure, door, key,
				boulder, sw, bomb, pit, hunter, strat,
				hound, coward, sword, arrow, ip, hp);
		itemList.getItems().addAll(itemL);
	}

	@FXML
	public void initialize() {
		dungeon.setHgap(5);
		dungeon.setVgap(5);
		dunSys.beforeReadMap();
		renderDungeon();
		addItemToList();
	}

	public void renderDungeon() {
    	for(int i = 0; i < dunSys.getSize(); i++) {
    		for(int j = 0; j < dunSys.getSize(); j++) {
    			StackPane s = buildStackPane(dunSys, i, j);
    			dungeon.addRow(i, s);
    		}
    	}
	}
	public StackPane buildStackPane(CreatorSystem dunSys, int i, int j) {
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



	private Label getDunIndex(int i) {
		Label coord = new Label();
		coord.setText(Integer.toString(i));
		coord.setPrefSize(40, 40);
		coord.setFont(Font.font(17));
		coord.setAlignment(Pos.CENTER);
		return coord;
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
		return makeImage("assets/ground.png");
	}

	public ImageView makeWall() {
		return makeImage("assets/wall.png");
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

	public ImageView makeTreasure() {
		return makeImage("assets/treasure.png");
	}

	public ImageView makeHoverPotion() {
		return makeImage("assets/potion_hover.png");
	}


	public ImageView makeInvincibilityPotion() {
		return makeImage("assets/potion_invis.png");
	}

	//used for test boulder and switch
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
	

    @FXML
    void startGame(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	Scene currentScene = node.getScene();
    	//startButton.setDisable(true);
    	//startButton.setText("Game Started!");

    	EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				if(key.equals(KeyCode.UP)) {
					dunSys.moveUp();
				} else if(key.equals(KeyCode.DOWN)) {
					dunSys.moveDown();
				} else if(key.equals(KeyCode.LEFT)) {
					dunSys.moveLeft();
				} else if(key.equals(KeyCode.RIGHT)) {
					dunSys.moveRight();
				} else if(key.equals(KeyCode.A)) {
					dunSys.attackArrow();
				} else if(key.equals(KeyCode.S)) {
					dunSys.attackSword();
				}// else if(key.equals(KeyCode.D)) {
//					dunSys.dropKey();
//				} else if (key.equals(KeyCode.L)) {
//					dunSys.placeBomb();
//				}

//				else if (key.equals(KeyCode.O)) {
//					dunSys.useKey();
//				}

				if(dunSys.getPlayer().getLives() < 0) {
					gameOver gscreen = new gameOver(currstage);
					gscreen.start();
				}

				dungeon.getChildren().clear();
				renderDungeon();
				updateInventory();
//				System.out.println(dunSys.getPlayer().getX() + " " + dunSys.getPlayer().getY());

				if(dunSys.checkWin()) {
					dunSys.getPlayer().getInventory().reset();
					WinScreen ws = new WinScreen(currstage);
					ws.start();
				}
			}
		};
		currentScene.setOnKeyPressed(ke);
    }

    @FXML
    void addToMap(ActionEvent event) {
    	String itemToAdd = new String();
    	itemToAdd = itemList.getValue();
    	String[] getType = itemToAdd.split("\\s+");
    	String x = xCoord.getText();
    	String y = yCoord.getText();
    	String item = getType[0] + " " + x + " " + y + " " + getType[1];
    	dunSys.addToMap(item);
    	//Thread.sleep(1000);
    	//clean the things up
    	dungeon.getChildren().clear();
    	dunSys.readMap(dunSys.getDungeon().getDungeon());
    	renderDungeon();
    }
    
    public void updateInventory() {
		item1.setText(Integer.toString(dunSys.getPlayer().getInventory().getArrowNum()));
		item2.setText(Integer.toString(dunSys.getPlayer().getInventory().getSwordNum()));
		item3.setText(Integer.toString(dunSys.getPlayer().getInventory().getBombNum()));
		item4.setText(Integer.toString(dunSys.getPlayer().getInventory().getKeyNum()));
		item5.setText(Integer.toString(dunSys.getPlayer().getInventory().getTreasureNum()));
		numLives.setText(Integer.toString(dunSys.getPlayer().getLives()));
	}
    
}
