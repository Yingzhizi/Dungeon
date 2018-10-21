package controller;

import Enemies.Coward;
import Enemies.Enemy;
import Enemies.Hound;
import Enemies.Hunter;
import Enemies.Strategist;
import ass2.CreatorSystem;
import collectables.Arrow;
import collectables.Bomb;
import collectables.Key;
import collectables.Sword;
import collectables.Treasure;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import structures.Switch;
import structures.Wall;

public class CreateGame {

	ObservableList itemL = FXCollections.observableArrayList();

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

	public CreateGame(Stage s, int size) {
		this.currstage = s;
		this.dungeonSize = size + 2;
		this.dunSys = new CreatorSystem();
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
		String pit = "Exit pit";
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
		dunSys.generateDungeon(dungeonSize);
		generateDungeon();
		addItemToList();
	}


	public void generateDungeon() {
		for(int i = 0; i < dunSys.getSize(); i++) {
			for(int j = 0; j < dunSys.getSize(); j++) {
				StackPane s = new StackPane();
				if(i == 0 || j == 0 || i == dungeonSize -1 || j == dungeonSize - 1){
					if(i == 0 && j != dungeonSize - 1|| j == 0 && i != dungeonSize -1){
						if(i != 0){
							Label coord = getDunIndex(i);
							s.getChildren().add(coord);
						}else if (j != 0) {
							Label coord = getDunIndex(j);
							s.getChildren().add(coord);
						}
					}
				}else {
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
						} else if (dunSys.getSquare(i, j).getSwitch() != null) {
							s.getChildren().add(makeSwitch());
						} else if (dunSys.getSquare(i,j).getBoulder() != null) {
							s.getChildren().add(makeBoulder());
						}
					}
				}
				if(dunSys.getPlayer().getX() == j && dunSys.getPlayer().getY() == i) {
					s.getChildren().add(makePlayer());
				}
				dungeon.addRow(i, s);

			}
		}
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
		return makeImage("assets/player.png");
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

    @FXML
    void startGame(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	Scene currentScene = node.getScene();

    	EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

			}

    	};
    }

	@FXML
	void AddSelectedItem(ActionEvent event) {

	}

    @FXML
    void addToMap(ActionEvent event) {
    	String itemToAdd = new String();
    	itemToAdd = itemList.getValue();
    	String[] getType = itemToAdd.split("\\s+");


    }
}
