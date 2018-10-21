package view;

import java.io.IOException;

import controller.HelpController;
import controller.StartController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpScreen {

	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public HelpScreen(Stage s) {
		this.s = s;
		this.title = "Help Screen";
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/help.fxml"));
	}
	
	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new HelpController(s));
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
