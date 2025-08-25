package org.example.wowantstobeamillionare;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.wowantstobeamillionare.controllers.sceneManager.SceneManager;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setPrimaryStage(stage);
        stage.setTitle("Who Wants to be a Millionaire");
        SceneManager.switchTo("welcome.fxml");
        stage.show();
    }
}
