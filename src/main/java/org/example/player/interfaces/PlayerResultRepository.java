package org.example.player.interfaces;

import java.sql.SQLException;
import java.sql.Statement;


public interface PlayerResultRepository {
    String loadPlayerResults();
    void savePlayerResults(Statement statement,String name, int score, String result) throws SQLException;
}
