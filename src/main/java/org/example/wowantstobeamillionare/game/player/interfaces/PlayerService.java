package org.example.wowantstobeamillionare.game.player.interfaces;

import org.example.wowantstobeamillionare.game.player.player.Player;

public interface PlayerService {
    Player createPlayer(String name);
    void updatePlayerScore(Player player, int score);
}
