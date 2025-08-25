package org.example.wowantstobeamillionare.game.starter;
import org.example.wowantstobeamillionare.game.game.gameInstance.Game;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

         boolean player = true;

         Game gameUser = new Game(player);
         gameUser.startGame();
    }
}