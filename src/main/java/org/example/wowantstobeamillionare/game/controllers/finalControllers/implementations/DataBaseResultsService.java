package org.example.wowantstobeamillionare.game.controllers.finalControllers.implementations;

import org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces.PlayerResultService;
import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnectionPool;
import org.example.wowantstobeamillionare.game.players.player.implementations.DefaultPlayerResultRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        try(Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
        ) {
            assert conn != null;
            try(Statement statement = conn.createStatement();){
                repository.savePlayerResults(statement, playerName, score, status);
            }
        } catch(SQLException exception){
            logger.log(Level.WARNING, exception.getMessage(), exception);
            throw exception;
        }
    }
}
