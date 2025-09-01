package org.example.wowantstobeamillionare.game.players.player.playerBehavior;

import org.example.wowantstobeamillionare.game.players.player.interfaces.InterfacePlayerService;

public class PlayerService implements InterfacePlayerService {
    @Override
    public Player createPlayer(String name) {
        return new Player(name);
    }
}
