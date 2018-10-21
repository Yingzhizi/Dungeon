package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.GameScreen;

public class DifficultyController {
    @FXML
    private Button easyBtn;

    @FXML
    private Button hardBtn;

    @FXML
    private Button mediumBtn;

    private Stage currStage;

    public DifficultyController(Stage s){
    	this.currStage = s;
    }

	@FXML
	public void initialize() {

	}

    @FXML
    public void handleEasyPlay(ActionEvent event) {
    	GameScreen cs = new GameScreen(currStage, "easy");
    	cs.start();
    }

    @FXML
    public void handleMidPlay(ActionEvent event) {
    	GameScreen cs = new GameScreen(currStage, "medium");
    	cs.start();
    }

    @FXML
    public void handleHardPlay(ActionEvent event) {
    	GameScreen cs = new GameScreen(currStage, "hard");
    	cs.start();
    }
}
