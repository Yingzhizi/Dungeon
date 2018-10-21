package view;

import java.io.IOException;

import controller.DifficultyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DifficultySelection {
	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;

	public DifficultySelection(Stage s) {
		this.s = s;
		this.title = "Select Difficulty";
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/hardSelect.fxml"));
	}

	public void start() {
		s.setTitle(this.title);
		fxmlLoader.setController(new DifficultyController(s));
		try {
			Parent root = fxmlLoader.load();
			Scene sc = new Scene(root, 600, 900);
			s.setScene(sc);
			s.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
