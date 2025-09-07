package org.example.wowantstobeamillionare.game.controllers.finalControllers.implementations;

import org.example.wowantstobeamillionare.game.controllers.finalControllers.classses.TopPlayer;
import org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces.TopPlayersFormater;

import java.util.List;

public class DefaultTopPlayersFormater implements TopPlayersFormater {
    @Override
    public String formatTopPlayers(List<TopPlayer> topPlayers) {
        if (topPlayers.size() < 3) {
            return "Not enough players for top 3 players";
        }
        StringBuilder sb = new StringBuilder("Top 3 players who won games \n");
        for (int i = 0; i < Math.min(3, topPlayers.size()); i++) {
            TopPlayer player = topPlayers.get(i);
            sb.append(player.getPlayerName()).append(" ").append(player.getTotalGames()).append("\n");
        }
        return sb.toString();
    }
}
