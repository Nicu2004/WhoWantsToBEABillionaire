package org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces;

import java.sql.SQLException;

public interface PlayerResultService {
    void savePlayerResult(String playerName, int score, String status)throws SQLException;
}
