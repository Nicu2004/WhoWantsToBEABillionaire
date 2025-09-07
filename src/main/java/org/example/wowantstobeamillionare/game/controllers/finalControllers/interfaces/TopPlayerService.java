package org.example.wowantstobeamillionare.game.controllers.finalControllers.interfaces;

import org.example.wowantstobeamillionare.game.controllers.finalControllers.classses.TopPlayer;

import java.sql.SQLException;
import java.util.List;

public interface TopPlayerService {
    List<TopPlayer> getTopPlayers() throws SQLException;
}
