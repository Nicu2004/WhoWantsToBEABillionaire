package org.example.wowantstobeamillionare.game.players.player.playerBehavior;

import javafx.scene.control.Alert;

public class PlayerNameValidationService {
    public boolean validatePlayerName(String playerName) {
        if(playerName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Name is Empty");
            alert.setHeaderText(null);
            alert.setContentText("Name cannot be empty");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
