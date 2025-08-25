package org.example.wowantstobeamillionare.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.wowantstobeamillionare.game.player.player.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

public class GameUIController {
    private static GameUIController instance;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label questionLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label playerLabel;
    @FXML private Button answerButton1;
    @FXML private Button answerButton2;
    @FXML private Button answerButton3;
    @FXML private Button answerButton4;

    private GameUIController() {}
    public static GameUIController getInstance() {
        if (instance == null) {
            instance = new GameUIController();
        }
        return instance;
    }

    public void initialize(Stage primaryStage) {
        this.stage = primaryStage;
    }
    private void loadFXML() {
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameUI.fxml"));
                loader.setController(this);
                Parent root = loader.load();

                scene = new Scene(root, 900, 600);
                stage.setScene(scene);
                stage.setTitle("Who Wants To Be A Millionaire");
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void initialize() {
        System.out.println("Initializing GameUIController");
        loadFXML();
    }
    public void setQuestion(Question q) {
        Platform.runLater(() -> {
            if(q!=null){
                questionLabel.setText(q.getQuestion());
                answerButton1.setText(q.getFirstAnswer());
                answerButton2.setText(q.getSecondAnswer());
                answerButton3.setText(q.getThirdAnswer());
                answerButton4.setText(q.getFourthAnswer());
            }
        });
    }
    public void setScore(int score) {
        Platform.runLater(() -> {
            scoreLabel.setText("Score: " + score);
        });
    }
    public void setPlayerName(String name)
    {
        Platform.runLater(() -> {
            playerLabel.setText(name);
        });
    }

    public void displayWelcomeMessage(boolean isPlayer, Player player) {
        System.out.println("welcome to the game");
    }

    public void showWinnerMessage(int score) {
        System.out.println("winner to the game");
    }

    public void showRegretMessage(int score) {
        System.out.println("regret to the game");
    }

    public void interractiveMessageForUser(Question q) {
        System.out.println("Your choice: ");
    }
}
