package org.example.wowantstobeamillionare.game.game.implementations;

import org.example.wowantstobeamillionare.game.game.interfaces.GameDisplay;
import org.example.wowantstobeamillionare.game.player.player.Player;
import org.example.wowantstobeamillionare.game.utils.Printer;

public class ConsoleGameDisplay implements GameDisplay {

    @Override
    public void displayWelcomeMessage(boolean isPlayer, Player player) {
        Printer.displayWelcomeMessage(isPlayer, player);
    }

    @Override
    public void displayPlayerResults(String results) {
//        results.forEach((name, result) -> {
//            System.out.println(name+ "->"+result);
//        });
    }

    @Override
    public void displayGameOver() {
        System.out.println("Game Over! Thanks for playing!");
    }
}
