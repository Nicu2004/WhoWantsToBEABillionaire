package org.example.wowantstobeamillionare.controllers.player;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.wowantstobeamillionare.controllers.sceneManager.SceneManager;
import org.example.wowantstobeamillionare.game.player.player.Player;
import java.sql.SQLException;

public class PlayerInterfaceController {

    static Player player;
    @FXML
    public TextField enterNameArea;
    @FXML
    public Button playButton;
    public String username;
    @FXML
    public void initialize() throws SQLException {
        System.out.println("PlayerController initialize");
    }
    @FXML
    public void initiatePlayerGame(ActionEvent actionEvent) {
        username = enterNameArea.getText();
        player = new Player(username);
        System.out.println("Username is "+player.getName());
        SceneManager.switchTo("playerGameScene.fxml");
    }
}
