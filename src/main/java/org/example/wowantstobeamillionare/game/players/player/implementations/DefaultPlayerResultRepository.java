package org.example.wowantstobeamillionare.game.players.player.implementations;

import org.example.wowantstobeamillionare.game.addon.DefaultTableCreator;
import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnectionPool;
import org.example.wowantstobeamillionare.game.players.player.interfaces.PlayerResultRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.example.wowantstobeamillionare.game.addon.DatabaseTableChecker.tableExists;


public class DefaultPlayerResultRepository implements PlayerResultRepository {


    public DefaultPlayerResultRepository() {

    }


    @Override
    public void savePlayerResults(Statement stmt, String name, int score, String result) throws SQLException {

            if(!tableExists("players")) {
                DefaultTableCreator.createUserTable();
            }
            try(Connection connection = DefaultDataBaseConnectionPool.create().getConnection())
            {
                String sql = "INSERT INTO Players (name, score, result, playDate) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = stmt.getConnection().prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, score);
                ps.setString(3, result.toUpperCase());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String date = sdf.format(cal.getTime());
                ps.setString(4, date);
                ps.executeUpdate();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
    }
}
