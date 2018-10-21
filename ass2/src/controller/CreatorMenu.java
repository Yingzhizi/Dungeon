package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.CreatorGame;

public class CreatorMenu {

	private Stage currStage;

    @FXML
    private VBox notAnInt;

    @FXML
    private TextField numberTF;

    @FXML
    private Button createBtn;

    @FXML
    private VBox outOfRange;

	public CreatorMenu(Stage s) {
		this.currStage = s;
	}

    @FXML
    void checkDigit(ActionEvent event) {
    	String toCheck = numberTF.getText();
    	if(checkIfNumber(toCheck)) {
    		handlePlay(Integer.parseInt(toCheck));
    	}
    }

    public void handlePlay(int size) {
    	CreatorGame cg = new CreatorGame(currStage, size);
    	cg.start();
    }

	private boolean checkIfNumber(String toCheck) {
		if(toCheck.equals("") || toCheck.equals(null)){
			notAnInt.setVisible(true);
			notAnInt.setManaged(true);

			outOfRange.setVisible(false);
			outOfRange.setManaged(false);
			return false;
		}
		if(toCheck.matches("^[0-9]*$")){
			int tfNumber = Integer.parseInt(toCheck);
			if (checkInRange(tfNumber)){
				return true;
			} else {
				outOfRange.setVisible(true);
				outOfRange.setManaged(true);

				notAnInt.setVisible(false);
				notAnInt.setManaged(false);
			}
		} else {
			notAnInt.setVisible(true);
			notAnInt.setManaged(true);

			outOfRange.setVisible(false);
			outOfRange.setManaged(false);
		}
		return false;
	}

	private boolean checkInRange(int toCheck) {
		if(toCheck > 20 || toCheck < 4) {
			return false;
		} else {
			return true;
		}
	}

}
