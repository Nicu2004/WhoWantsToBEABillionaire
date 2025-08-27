package org.example.wowantstobeamillionare.game.controllers.player.interfaces;

import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.Player;

public interface PlayerService {

    Player createPlayer(String name);
    void updatePlayerScore(Player player, int score);
}
