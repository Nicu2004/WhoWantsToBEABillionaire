package org.example.starter;

import org.example.gameInstance.Game;
import org.example.dbIMplemenations.pgDatabaseConnect;
import org.example.database.databaseConnection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {


        pgDatabaseConnect pg = new pgDatabaseConnect();
        databaseConnection con = new databaseConnection(pg);
        con.initiateDataBaseConnection(pg);
         boolean player = true;

         Game gameUser = new Game(player);
         gameUser.startGame();
    }
}