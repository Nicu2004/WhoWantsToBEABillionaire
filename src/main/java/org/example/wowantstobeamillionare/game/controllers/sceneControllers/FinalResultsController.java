package org.example.wowantstobeamillionare.game.controllers.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.wowantstobeamillionare.game.addon.PlayersTopGames;
import org.example.wowantstobeamillionare.game.addon.TopPlayersClass;
import org.example.wowantstobeamillionare.game.players.player.implementations.DefaultPlayerResultRepository;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.database.PgStatemantClass;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalResultsController {

    @FXML
    public Button disableFullScreen;
    @FXML
    public Text topPLayersList;
    private String status;
    PlayersTopGames playersTopGames;
    private final DefaultPlayerResultRepository playerResultRepository;

    public FinalResultsController() throws SQLException {

        playerResultRepository = new DefaultPlayerResultRepository();
        playersTopGames = new PlayersTopGames();
    }
    public void setPlayerFinalResults(Player player) throws SQLException {
        System.out.println("Player "+player.getName()+" has been set");
        status = (player.getScore()==5)?"WON":"LOST";
        loadToDataBase(player);
        setPlayersList();
    }
    private void setPlayersList() throws SQLException {
        List<TopPlayersClass>top3Players = playersTopGames.loadTopPlayers();
        String sb = "The top 3 players who won games \n"+
                    top3Players.getFirst().getPlayerName() +" " +top3Players.getFirst().getTotalGames() + "\n" +
                    top3Players.get(1).getPlayerName() +" " + top3Players.get(1).getTotalGames() + "\n" +
                    top3Players.get(2).getPlayerName() +" " + top3Players.get(2).getTotalGames() + "\n";
        topPLayersList.setText(sb);
    }

    private void loadToDataBase(Player player)  {

        try {
            playerResultRepository.savePlayerResults(PgStatemantClass.createStmt(), player.getName(), player.getScore(), status);
        }catch (SQLException ex) {
            Logger.getLogger(FinalResultsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void setDisableFullScreen(ActionEvent event) throws SQLException {
        Stage stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
