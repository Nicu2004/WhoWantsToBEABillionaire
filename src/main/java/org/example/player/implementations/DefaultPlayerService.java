package org.example.player.implementations;

import org.example.player.interfaces.PlayerService;
import org.example.player.player.Player;

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
