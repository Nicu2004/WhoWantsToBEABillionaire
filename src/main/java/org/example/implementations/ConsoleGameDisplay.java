package org.example.implementations;

import org.example.interfaces.GameDisplay;
import org.example.objects.player.Player;
import org.example.utils.Printer;

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
