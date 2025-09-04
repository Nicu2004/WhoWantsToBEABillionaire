package org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.game.addon.PlayersTopGames;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.classses.PlayerGameResults;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.classses.TopPlayer;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.implementations.DataBaseResultsService;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.implementations.DatabaseTopPlayersService;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.implementations.DefaultTopPlayersFormater;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.implementations.JavaFXWindowManager;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.interfaces.PlayerResultService;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.interfaces.TopPlayerService;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.interfaces.TopPlayersFormater;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.finalControllers.interfaces.WindowManager;
import org.example.wowantstobeamillionare.game.players.player.implementations.DefaultPlayerResultRepository;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import java.sql.SQLException;
import java.util.List;


public class FinalResultsController {

    @FXML
    public Button disableFullScreen;
    @FXML
    public Text topPLayersList;

    private final PlayerResultService playerResultService;
    private final TopPlayerService topPlayersService;
    private final WindowManager windowManager;
    private final TopPlayersFormater formater;

    public FinalResultsController(PlayerResultService playerResultService,
                                  TopPlayerService topPlayersService,
                                  WindowManager windowManager,
                                  TopPlayersFormater formater) {
        this.playerResultService = playerResultService;
        this.topPlayersService = topPlayersService;
        this.windowManager = windowManager;
        this.formater = formater;
    }
    public FinalResultsController()throws SQLException
    {
        this(
                new DataBaseResultsService(new DefaultPlayerResultRepository()),
                new DatabaseTopPlayersService(new PlayersTopGames()),
                new JavaFXWindowManager(),
                new DefaultTopPlayersFormater()
        );
    }
    public void setPlayerFinalResults(Player player) throws SQLException
    {
        System.out.println("Player "+player.getName()+" has been successfully set");

        PlayerGameResults result = new PlayerGameResults(
                player.getName(),
                player.getScore(),
                PlayerGameResults.GameStatus.fromScore(player.getScore())
                );
        savePlayerResult(result);
        displayTopPLayers();
    }
    private void savePlayerResult(PlayerGameResults results) throws SQLException
    {
        playerResultService.savePlayerResult(
                results.getPlayerName(),
                results.getScore(),
                results.getStatus().toString()
        );
    }
    private void displayTopPLayers() throws SQLException
    {
        List<TopPlayer> topPlayers = topPlayersService.getTopPlayers();
        String formattedTopPlayers = formater.formatTopPlayers(topPlayers);
        topPLayersList.setText(formattedTopPlayers);
    }
    @FXML
    public void setDisableFullScreen(ActionEvent event){
        windowManager.closeWindow(event);
    }


}
