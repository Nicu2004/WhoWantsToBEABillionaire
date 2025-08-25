package org.example.wowantstobeamillionare.game.game.interfaces;

import org.example.wowantstobeamillionare.game.player.player.Player;

public interface GameDisplay {
    void displayWelcomeMessage(boolean isPlayer, Player player);
    void displayPlayerResults(String results);
    void displayGameOver();
}
