package org.example.wowantstobeamillionare.game.database;

import java.sql.*;
import java.util.Properties;
import java.sql.Connection;


public class DefaultDataBaseConnection{

    private static volatile DefaultDataBaseConnection instance;
    private static volatile  Connection connection;
    DefaultDataBaseConnection() {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        try{
            connection = DriverManager.getConnection(url, props);
        }catch (SQLException sqlException)
        {
            System.out.println("connection not succesful");
            System.out.println(sqlException.getMessage());
        }
    }
    public static void getInstance()
    {
        if(instance==null) {
            synchronized (DefaultDataBaseConnection.class) {
                if (instance == null) {
                    instance = new DefaultDataBaseConnection();
                }
            }
        }
    }
    public static Connection getConn() {
        DefaultDataBaseConnection.getInstance();
        return connection;
    }

}
