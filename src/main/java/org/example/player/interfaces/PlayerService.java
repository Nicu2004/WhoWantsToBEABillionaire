package org.example.player.interfaces;

import org.example.player.player.Player;

public interface PlayerService {
    Player createPlayer(String name);
    void updatePlayerScore(Player player, int score);
}
