package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.StartScreen;

public class GameOverController {

	private Stage currStage;
	
	@FXML
	private Button mainMenu;
	
	public GameOverController(Stage s) {
		this.currStage = s;
	}
	
	@FXML
	public void intialize() {}
	
	@FXML
	public void handleMainMenu() {
		StartScreen ss = new StartScreen(currStage);
		ss.start();
	}
	
	
}
