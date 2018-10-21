package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.HelpScreen;
import view.StartScreen;

public class HelpController {

	private Stage currStage;
	
	@FXML
	private Button backButton;
	
	public HelpController(Stage s) {
		this.currStage = s;
	}
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void handleBack() {
		StartScreen s = new StartScreen(currStage);
		s.start();
	}
	
}
