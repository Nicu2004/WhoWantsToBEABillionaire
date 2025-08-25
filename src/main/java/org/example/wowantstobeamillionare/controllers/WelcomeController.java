package org.example.wowantstobeamillionare.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.wowantstobeamillionare.controllers.sceneManager.SceneManager;

import java.sql.SQLException;
import java.util.Objects;

public class WelcomeController {

    @FXML
    public GridPane welcomeScene;
    @FXML
    public Button guestMode;
    @FXML
    public Button playerMode;
    @FXML
    public VBox questInterface;
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView imageView;
    private boolean isPlayer;
    public void initialize() {

        welcomeText.setText("Welcome to who wants to be a millionaire!");
        // Use absolute path starting with "/"
        Image image = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/org/example/wowantstobeamillionare/images/Copilot_20250823_153427.png")
        ));
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setImage(image);
    }
    public void onPlayerModeClicked(ActionEvent actionEvent) throws SQLException {
        isPlayer = true;
        System.out.println("player mode clicked");
        SceneManager.switchTo("playerInterface.fxml");
    }
    public void onGuestModeClicked(ActionEvent actionEvent) throws SQLException {
        System.out.println("guest mode clicked");
        isPlayer = false;

        SceneManager.switchTo("guestInterface.fxml");
    }
}
