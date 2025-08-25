package org.example.wowantstobeamillionare.controllers.sceneManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage primaryStage;
    public static void setPrimaryStage(Stage stage)
    {
        primaryStage=stage;
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
