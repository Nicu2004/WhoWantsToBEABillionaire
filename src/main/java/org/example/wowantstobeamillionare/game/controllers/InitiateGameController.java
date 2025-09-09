package org.example.wowantstobeamillionare.game.controllers;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.PlayerNameValidationService;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.PlayerService;

public class InitiateGameController {

    private Player player;
    private PlayerService playerService;
    private PlayerNameValidationService playerNameValidationService;

    @FXML
    public TextField enterNameArea;
    @FXML
    public Button playButton;
    public String username;


    public InitiateGameController()
    {
        playerNameValidationService = new PlayerNameValidationService();
        playerService = new PlayerService();
    }
    @FXML
    public void initialize() {

    }
    @FXML
    public void initiatePlayerGame() {
       if(!playerNameValidationService.validatePlayerName(enterNameArea.getText()))
       {
           return;
       }
        player = playerService.createPlayer(enterNameArea.getText());
        SceneManager.switchTo("playerGameScene.fxml" , player);
    }
}
