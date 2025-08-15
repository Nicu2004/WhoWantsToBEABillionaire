package org.example.game.interfaces;

import org.example.player.player.Player;

public interface GameDisplay {
    void displayWelcomeMessage(boolean isPlayer, Player player);
    void displayPlayerResults(String results);
    void displayGameOver();
}
