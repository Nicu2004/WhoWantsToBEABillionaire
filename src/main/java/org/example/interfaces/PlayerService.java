package org.example.interfaces;

import org.example.objects.player.Player;

public interface PlayerService {
    Player createPlayer(String name);
   void updatePlayerScore(Player player, int score);
}
