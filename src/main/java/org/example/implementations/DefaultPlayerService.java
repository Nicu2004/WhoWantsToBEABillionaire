package org.example.implementations;

import org.example.interfaces.PlayerService;
import org.example.objects.player.Player;

public class DefaultPlayerService implements PlayerService {

    @Override
    public Player createPlayer(String name) {
        return new Player(name);
    }

    @Override
    public void updatePlayerScore(Player player, int score) {
        player.setScore(score);
    }
}
