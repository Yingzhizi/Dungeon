package view;

import java.io.IOException;

import controller.HelpController;
import controller.WinScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WinScreen {

	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public WinScreen(Stage s) {
		this.s = s;
		this.title = "Winner!";
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/win.fxml"));
	}
	
	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new WinScreenController(s));
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
