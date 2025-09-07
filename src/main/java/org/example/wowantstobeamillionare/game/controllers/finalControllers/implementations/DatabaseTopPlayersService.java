package org.example.wowantstobeamillionare.game.controllers.finalControllers.implementations;

import org.example.wowantstobeamillionare.game.addon.PlayersTopGames;
import org.example.wowantstobeamillionare.game.addon.TopPlayersClass;
import org.example.wowantstobeamillionare.game.controllers.finalControllers.classses.TopPlayer;
import org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces.TopPlayerService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseTopPlayersService implements TopPlayerService {

    private final PlayersTopGames playersTopGames;

    public DatabaseTopPlayersService(PlayersTopGames playersTopGames)
    {
        this.playersTopGames = playersTopGames;
    }

    @Override
    public List<TopPlayer> getTopPlayers() throws SQLException {
        List<TopPlayersClass> topPlayersData = playersTopGames.loadTopPlayers();
        return topPlayersData.stream().map(tp->new TopPlayer(tp.getPlayerName(), tp.getTotalGames())).collect(Collectors.toList());
    }
}
