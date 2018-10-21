package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.StartScreen;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setHeight(600);
        primaryStage.setWidth(900);

        
        StartScreen startScreen = new StartScreen(primaryStage);
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}