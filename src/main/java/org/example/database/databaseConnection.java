package org.example.database;
import java.sql.*;
import java.util.Properties;

public class databaseConnection {


    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("Connected to database successfully "+conn.toString());
        Statement st = conn.createStatement();
        boolean rs = st.execute("" +
                "CREATE TABLE IF NOT EXISTS " +
                "questions " +
                "(name varchar(30), " +
                "last_name varchar(30), " +
                "years INT) " +
                "");
        System.out.println("Table created successfully "+rs);


    }
}
