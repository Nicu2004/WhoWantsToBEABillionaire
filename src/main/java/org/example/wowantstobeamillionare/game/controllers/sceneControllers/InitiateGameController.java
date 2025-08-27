package org.example.wowantstobeamillionare.game.controllers.sceneControllers.sceneManager;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.PlayerGameScene;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.PlayerNameValidationService;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.PlayerService;

public class PlayerInitiateGame {

    private Player player;
    private PlayerService playerService;
    private PlayerNameValidationService playerNameValidationService;

    @FXML
    public TextField enterNameArea;
    @FXML
    public Button playButton;
    public String username;


    public PlayerInitiateGame()
    {
        playerNameValidationService = new PlayerNameValidationService();
        playerService = new PlayerService();
    }
    @FXML
    public void initialize() {
        System.out.println("PlayerController initialize");
    }
    @FXML
    public void initiatePlayerGame() {
       if(!playerNameValidationService.validatePlayerName(enterNameArea.getText()))
       {
           return;
       }
        player = playerService.createPlayer(enterNameArea.getText());
        System.out.println("Username is "+ player.getName());
        SceneManager.switchTo("playerGameScene.fxml" , player);
    }
}
