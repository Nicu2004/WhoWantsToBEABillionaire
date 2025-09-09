package org.example.wowantstobeamillionare.game.addon;

import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTableChecker {

    public static boolean tableExists(String tableName) {
        String sql = "SELECT EXISTS (" +
                "SELECT FROM information_schema.tables " +
                "WHERE table_schema = 'public' " +
                "AND table_name = ?)";


        try(Connection connection = DefaultDataBaseConnectionPool.create().getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, tableName);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
        return false;
    }

}
