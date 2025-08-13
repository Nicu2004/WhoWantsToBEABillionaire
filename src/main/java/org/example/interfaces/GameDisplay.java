package org.example.interfaces;

import org.example.objects.player.Player;
import org.example.objects.player.PlayerResults;

import java.util.Map;

public interface GameDisplay {
    void displayWelcomeMessage(boolean isPlayer, Player player);
    void displayPlayerResults(Map<String, PlayerResults> results);
    void displayGameOver();
}
