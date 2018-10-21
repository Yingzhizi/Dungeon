package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import controller.StartController;

public class StartScreen {

    private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;

    public StartScreen(Stage s) {
        this.s = s;
        this.title = "Start Screen";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/start.fxml"));
    }

    public void start()  {
        s.setTitle(title);
        fxmlLoader.setController(new StartController(s));
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
