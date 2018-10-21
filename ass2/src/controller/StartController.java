package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.CreatorSelectScreen;
import view.DifficultySelection;
import view.HelpScreen;


public class StartController {

	private Stage currStage;

	@FXML
	private Button playButton;

	@FXML
	private Button makeButton;
	
	@FXML
	private Button helpButton;

	public StartController(Stage s) {
		this.currStage = s;
	}

	@FXML
	public void initialize() {

	}

	@FXML
	public void handlePlay() {
		DifficultySelection ds = new DifficultySelection(currStage);
		ds.start();
	}

	@FXML
	public void handleMake() {
		CreatorSelectScreen css = new CreatorSelectScreen(currStage);
		css.start();
	}
	
	@FXML 
	public void handleHelp() {
		HelpScreen hs = new HelpScreen(currStage);
		hs.start();
	}

}
