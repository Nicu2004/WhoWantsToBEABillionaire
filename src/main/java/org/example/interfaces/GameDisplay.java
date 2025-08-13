package org.example.interfaces;

import org.example.objects.player.Player;

public interface GameDisplay {
    void displayWelcomeMessage(boolean isPlayer, Player player);
    void displayPlayerResults(String results);
    void displayGameOver();
}
