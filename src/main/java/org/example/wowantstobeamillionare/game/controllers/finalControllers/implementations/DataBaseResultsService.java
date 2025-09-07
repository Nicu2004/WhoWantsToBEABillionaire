package org.example.wowantstobeamillionare.game.controllers.finalControllers.implementations;

import org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces.PlayerResultService;
import org.example.wowantstobeamillionare.game.database.PgStatemantClass;
import org.example.wowantstobeamillionare.game.players.player.implementations.DefaultPlayerResultRepository;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseResultsService implements PlayerResultService {

    private final DefaultPlayerResultRepository repository;
    private final Logger logger;
    public DataBaseResultsService(DefaultPlayerResultRepository repository) {
        this.repository = repository;
        this.logger = Logger.getLogger(DataBaseResultsService.class.getName());
    }
    @Override
    public void savePlayerResult(String playerName, int score, String status) throws SQLException {
        try{
            repository.savePlayerResults(PgStatemantClass.createStmt(), playerName, score, status);
        }catch(SQLException ex){
            logger.log(Level.WARNING, ex.getMessage(), ex);
            throw ex;
        }
    }
}
