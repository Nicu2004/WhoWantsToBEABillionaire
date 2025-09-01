package org.example.wowantstobeamillionare.game.players.player.interfaces;

import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;

public interface PlayerService {

    Player createPlayer(String name);
    void updatePlayerScore(Player player, int score);
}
