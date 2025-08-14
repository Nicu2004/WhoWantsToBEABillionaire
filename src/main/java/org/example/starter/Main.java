package org.example.starter;
import org.example.gameInstance.Game;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

         boolean player = true;

         Game gameUser = new Game(player);
         gameUser.startGame();
    }
}