package org.example.wowantstobeamillionare.game.controllers.player.interfaces;

import java.sql.SQLException;
import java.sql.Statement;


public interface PlayerResultRepository {


    void savePlayerResults(Statement statement,String name, int score, String result) throws SQLException;

}
