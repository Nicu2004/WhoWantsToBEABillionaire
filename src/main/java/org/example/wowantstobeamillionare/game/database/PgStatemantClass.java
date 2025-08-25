package org.example.wowantstobeamillionare.game.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PgStatemantClass {

    public static Statement createStmt() throws SQLException{
        Connection connection = DefaultDataBaseConnection.getConn();
        return connection.createStatement();
        //this is a comment
    }

}
