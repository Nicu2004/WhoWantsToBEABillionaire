package org.example.wowantstobeamillionare.game.controllers;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.example.wowantstobeamillionare.game.controllers.finalControllers.FinalResultsController;
import org.example.wowantstobeamillionare.game.controllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;

public class SceneManager {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchTo(String fxmlFile, Player player) {
        try {
            primaryStage.setFullScreen(true);
            primaryStage.setResizable(false);
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/example/wowantstobeamillionare/" + fxmlFile));

            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof GameEngineSceneController) {
                ((GameEngineSceneController) controller).setPlayer(player);
            } else if (controller instanceof FinalResultsController) {
                ((FinalResultsController) controller).setPlayerFinalResults(player);
            }
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setResizable(false);
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        } catch (Exception e) {
           throw new  RuntimeException(e);
        }
    }

    public static void switchTo(String fxmlFile) {

        try {
            primaryStage.setFullScreen(true);
            primaryStage.setResizable(false);
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/example/wowantstobeamillionare/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setResizable(false);
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        if (primaryStage == null)
            return primaryStage;
        else {
            return null;
        }
    }
}
