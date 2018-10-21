package view;

import java.io.IOException;

import controller.CreatorMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreatorSelectScreen {
	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;

	public CreatorSelectScreen (Stage s) {
		this.s = s;
		this.title = "Creator Options";
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/createMenu.fxml"));

	}

	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new CreatorMenu(s));
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
