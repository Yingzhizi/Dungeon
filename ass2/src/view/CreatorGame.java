package view;

import java.io.IOException;

import controller.CreateGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreatorGame {
	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	private int dungeonSize;

	public CreatorGame(Stage s, int dungeonSize) {
		this.s = s;
		this.title = "Creation Mode";
		this.dungeonSize = dungeonSize;
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/creatorGame.fxml"));
	}

	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new CreateGame(s, dungeonSize));
		try {
			Parent root = fxmlLoader.load();
			Scene sc = new Scene(root, 600, 900);
			s.setScene(sc);
			s.show();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
