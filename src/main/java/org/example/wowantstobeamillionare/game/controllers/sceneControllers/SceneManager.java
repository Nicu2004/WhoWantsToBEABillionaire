package org.example.wowantstobeamillionare.game.controllers.sceneControllers.sceneManager;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.PlayerGameScene;

public class SceneManager{

    private static Stage primaryStage;
    public static void setPrimaryStage(Stage stage)
    {
        primaryStage=stage;
    }
    public static void switchTo(String fxmlFile, Player player)
    {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/example/wowantstobeamillionare/"+fxmlFile));
            System.out.println(fxmlFile);
            Parent root = loader.load();

            Object controller = loader.getController();

            if(controller instanceof PlayerGameScene)
            {
                ((PlayerGameScene)controller).setPlayer(player);
            }
            else if(controller instanceof PlayerFinalResults)
            {
                ((PlayerFinalResults)controller).setPlayerFinalResults(player);
            }
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
        }catch (Exception e)
            {
            e.printStackTrace();
            }


    }
    public static void switchTo(String fxmlFile)
    {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/example/wowantstobeamillionare/"+fxmlFile));
            System.out.println(fxmlFile);
            Parent root = loader.load();
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
