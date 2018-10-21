package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.StartScreen;
import view.WinScreen;
import view.gameOver;

import java.util.Random;

import ass2.DungeonSystem;

public class GameController {

	private Stage currStage;
	private DungeonSystem dunSys;
	private String diff;
	private DungeonGUIGenerator gui;

	@FXML
	private GridPane dungeon;

	@FXML
	private Button startButton;

	@FXML
	private Button exitButton;

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
	@FXML
	private Label objectives;


	public GameController(Stage s, String difficulty) {
		this.currStage = s;
		this.dunSys = new DungeonSystem();
		this.diff = difficulty;
		this.gui = new DungeonGUIGenerator();
	}

	@FXML
	public void initialize() {
		dungeon.setHgap(5);
		dungeon.setVgap(5);
		//dunSys.readFromText(this.diff);
		//randomly choose the dungeon map
		Random rand = new Random();
		int x = rand.nextInt(3);
		if (this.diff.equals("easy")) {
			switch(x) {
				case 0:
					dunSys.easyLevel1();
					break;
				case 1:
					dunSys.easyLevel2();
					break;
				case 2:
					dunSys.easyLevel3();
					break;
			}	
		} else if (this.diff.equals("medium")) {
			switch(x) {
				case 0:
					dunSys.mediumLevel1();
					break;
				case 1:
					dunSys.mediumLevel2();
					break;
				case 2:
					dunSys.mediumLevel3();
					break;
				
			}
		} else if (this.diff.equals("hard")) {
			switch(x) {
			case 0:
				dunSys.hardLevel1();
				break;
			case 1:
				dunSys.hardLevel2();
				break;
			case 2:
				dunSys.hardLevel1();
				break;
			
		}
		}
    	renderDungeon();
    	updateInventory();
    	item1.setText("0");
    	item2.setText("0");
    	item3.setText("0");
    	item4.setText("0");
    	item5.setText("0");
    	numLives.setText(Integer.toString(dunSys.getPlayer().getLives()));
    	objectives.setText(dunSys.getWin().toString());
	}

	@FXML
	public void handleExit() {
		dunSys.getPlayer().getInventory().reset();
		StartScreen sc = new StartScreen(currStage);
		sc.start();
	}


	// handles the player input
	@FXML
	public void handleStartButton(ActionEvent event) {

	    	Node node = (Node) event.getSource();
	    	Scene currentScene = node.getScene();
	    	startButton.setDisable(true);
	    	startButton.setText("Game Started!");

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
					} else if(key.equals(KeyCode.D)) {
						dunSys.dropKey();
					} else if (key.equals(KeyCode.L)) {
						dunSys.placeBomb();
					}

					if(dunSys.getPlayer().getLives() < 0) {
						gameOver gscreen = new gameOver(currStage);
						gscreen.start();
					}

					dungeon.getChildren().clear();
					renderDungeon();
					updateInventory();

					if(dunSys.checkWin()) {
						dunSys.getPlayer().getInventory().reset();
						WinScreen ws = new WinScreen(currStage);
						ws.start();
					}
				}
			};
			currentScene.setOnKeyPressed(ke);
	}

	public void updateInventory() {
		item1.setText(Integer.toString(dunSys.getPlayer().getInventory().getArrowNum()));
		item2.setText(Integer.toString(dunSys.getPlayer().getInventory().getSwordNum()));
		item3.setText(Integer.toString(dunSys.getPlayer().getInventory().getBombNum()));
		item4.setText(Integer.toString(dunSys.getPlayer().getInventory().getKeyNum()));
		item5.setText(Integer.toString(dunSys.getPlayer().getInventory().getTreasureNum()));
		numLives.setText(Integer.toString(dunSys.getPlayer().getLives()));
	}


	public void renderDungeon() {
    	for(int i = 0; i < dunSys.getSize(); i++) {
    		for(int j = 0; j < dunSys.getSize(); j++) {
    			StackPane s = gui.buildStackPane(dunSys, i, j);
    			dungeon.addRow(i, s);
    		}
    	}
	}

}
