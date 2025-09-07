package org.example.wowantstobeamillionare.game.database;

import java.sql.*;
import java.util.Properties;
import java.sql.Connection;


public class DefaultDataBaseConnection {

    private static volatile DefaultDataBaseConnection instance;
    private static volatile Connection connection;

    private DefaultDataBaseConnection() {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException sqlException) {
            System.out.println("connection not successful");
            System.out.println(sqlException.getMessage());
        }
    }

    public static DefaultDataBaseConnection getInstance() {
        if (instance == null) {
            synchronized (DefaultDataBaseConnection.class) {
                if (instance == null) {
                    instance = new DefaultDataBaseConnection();
                }
            }
        }
        return instance;
    }

    public static Connection getConn() {
        if(instance==null){
            getInstance();
        }
        try{
            if(connection!=null || !connection.isClosed() && connection.isValid(2)){
                System.out.println("Connection Successful");
                return connection;
            }
        }catch(SQLException sqlException){
            System.out.println("connection not successful");
        }
        System.out.println("Connection Failed! Check output console");
        return null;
    }
}
