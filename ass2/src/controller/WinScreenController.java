package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.StartScreen;

public class WinScreenController {

	private Stage currStage;
	
	@FXML
	private Button mainMenu;
	
	@FXML
	private Button exit;
	
	public WinScreenController(Stage s) {
		this.currStage = s;
	}
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void handleExit() {
		System.exit(0);
	}
	
	@FXML
	public void handleMainMenu() {
		StartScreen ss = new StartScreen(currStage);
		ss.start();
	}
}
