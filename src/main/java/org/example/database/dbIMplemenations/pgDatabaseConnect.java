package org.example.database.dbIMplemenations;

import org.example.database.dbINterface.databaseConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class pgDatabaseConnect implements databaseConnect {
    private Connection connection;

    @Override
    public void connect() throws SQLException {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("Connected to database successfully " + conn.toString());
        this.connection = conn;
    }

    public Connection getConnection() throws SQLException {
        return this.connection;
    }
}