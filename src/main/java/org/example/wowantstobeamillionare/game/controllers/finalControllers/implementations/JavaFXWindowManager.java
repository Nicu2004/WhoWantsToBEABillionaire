package org.example.wowantstobeamillionare.game.controllers.finalControllers.implementations;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces.WindowManager;

public class JavaFXWindowManager implements WindowManager {

    @Override
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
