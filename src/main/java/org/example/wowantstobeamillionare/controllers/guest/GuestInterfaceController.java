package org.example.wowantstobeamillionare.controllers.guest;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.controllers.sceneManager.SceneManager;

public class GuestInterfaceController {

    @FXML
    public Text welcomeGuest;
    @FXML private Button playButton;

    public void initialize() {
        welcomeGuest.setText("Welcome to Game Trial");
    }

    public void playTrialGame(ActionEvent actionEvent) {
        SceneManager.switchTo("guestGameScene.fxml");
    }
}
