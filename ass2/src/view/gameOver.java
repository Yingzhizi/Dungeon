package view;

import java.io.IOException;

import controller.GameOverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class gameOver {

	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public gameOver(Stage s) {
		this.s = s;
		this.title = "Game Over Screen";
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/gameOver.fxml"));
	}
	
	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new GameOverController(s));
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
