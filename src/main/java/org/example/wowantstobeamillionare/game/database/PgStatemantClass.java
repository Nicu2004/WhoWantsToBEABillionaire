package org.example.wowantstobeamillionare.game.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PgStatemantClass {

    public static Statement createStmt() throws SQLException{
        if(DefaultDataBaseConnection.getConn()!=null){
            Connection connection = DefaultDataBaseConnection.getConn();
            System.out.println("Connected to database.");
            return connection.createStatement();
        }
        System.out.println("Statement is null.");
        return null;
        //this is a comment
    }

}
