package org.example.player.implementations;

import org.example.database.dbIMplemenations.pgDatabaseConnect;
import org.example.player.interfaces.PlayerResultRepository;
import org.example.player.player.PlayerResultLoader;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FilePlayerResultRepository implements PlayerResultRepository {
    private final String fileName;

    public FilePlayerResultRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String loadPlayerResults() {
        // Use your existing PlayerResultLoader
        return PlayerResultLoader.loadPlayerResults(fileName);
    }

    @Override
    public void savePlayerResults(pgDatabaseConnect pg, String name, int score, String result) throws SQLException {
        String sql = "INSERT INTO Players (name, score, result, playDate) VALUES (?, ?, ?, ?)";;
        PreparedStatement ps = pg.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, score);
        ps.setString(3, result.toUpperCase());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        ps.setString(4, date);
        ps.executeUpdate();
    }
}
