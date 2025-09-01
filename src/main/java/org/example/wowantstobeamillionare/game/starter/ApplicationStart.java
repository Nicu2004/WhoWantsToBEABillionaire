package org.example.wowantstobeamillionare.game.starter;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.SceneManager;

public class ApplicationStart extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.setPrimaryStage(stage);
        stage.setTitle("Who Wants to be a Millionaire");
        SceneManager.switchTo("welcome.fxml");
        stage.show();
    }

    public static class Launcher {
        public static void main(String[] args) {
            launch(ApplicationStart.class, args);
        }
    }
}
