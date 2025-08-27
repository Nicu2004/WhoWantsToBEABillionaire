package org.example.wowantstobeamillionare.game.controllers.sceneControllers.sceneManager;

import org.example.wowantstobeamillionare.game.controllers.player.implementations.DefaultPlayerResultRepository;
import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.database.PgStatemantClass;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerFinalResults {

    private String status;
    private Player player;
    private final DefaultPlayerResultRepository playerResultRepository;

    public PlayerFinalResults()  {
        playerResultRepository = new DefaultPlayerResultRepository();
    }
    public void setPlayerFinalResults(Player player) {
        this.player = player;
        System.out.println("Player "+player.getName()+" has been set");
        status = (player.getScore()==5)?"WON":"LOST";
        loadToDataBase(player);
    }
    private void loadToDataBase(Player player)  {
        try {
            playerResultRepository.savePlayerResults(PgStatemantClass.createStmt(), player.getName(), player.getScore(), status);
        }catch (SQLException ex) {
            Logger.getLogger(PlayerFinalResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
