package org.example.wowantstobeamillionare.game.players.player.implementations;

import org.example.wowantstobeamillionare.game.players.player.interfaces.PlayerService;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;

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
