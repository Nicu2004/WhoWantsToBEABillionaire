package org.example.wowantstobeamillionare.game.players.player.implementations;

import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnection;
import org.example.wowantstobeamillionare.game.players.player.interfaces.PlayerResultRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DefaultPlayerResultRepository implements PlayerResultRepository {


    public DefaultPlayerResultRepository() {

    }


    @Override
    public void savePlayerResults(Statement stmt, String name, int score, String result) throws SQLException {

        if(DefaultDataBaseConnection.getConn()!=null) {
            String sql = "INSERT INTO Players (name, score, result, playDate) VALUES (?, ?, ?, ?)";;
            PreparedStatement ps = stmt.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.setString(3, result.toUpperCase());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String date = sdf.format(cal.getTime());
            ps.setString(4, date);
            ps.executeUpdate();
        }
        else {
            System.out.println("Connection Failed! Check output console");
        }

    }
}
