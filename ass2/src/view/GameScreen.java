package view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import controller.GameController;

public class GameScreen {
    private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;
    private String diff;

    public GameScreen(Stage s, String difficulty) {
    	this.diff = difficulty;
        this.s = s;
        this.title = "Game Screen";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/game.fxml"));
    }

    public void start() {
        s.setTitle(title);
        fxmlLoader.setController(new GameController(s, diff));
        try {
        	Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500, 300);
            s.setScene(sc);
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}