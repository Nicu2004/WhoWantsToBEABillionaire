package org.example.interfaces;

import org.example.database.dbIMplemenations.pgDatabaseConnect;

import java.sql.SQLException;


public interface PlayerResultRepository {
    String loadPlayerResults();
    void savePlayerResults(pgDatabaseConnect pg, String name, int score, String result) throws SQLException;
}
