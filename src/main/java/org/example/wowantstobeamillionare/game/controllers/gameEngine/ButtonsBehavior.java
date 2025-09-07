package org.example.wowantstobeamillionare.game.controllers.gameEngine;

import javafx.scene.control.Button;

public class ButtonsBehavior {

    public static void disableAllButtons(Button button1,  Button button2, Button button3, Button button4) {
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
    }
    public static void enableAllButtons(Button button1, Button button2, Button button3, Button button4) {
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        button4.setDisable(false);
    }
}
