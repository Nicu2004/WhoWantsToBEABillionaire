package org.example.wowantstobeamillionare.game.addon;

import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnectionPool;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static org.example.wowantstobeamillionare.game.addon.Log4j.logger;

public class PlayersTopGames{

    private List<Player> players;
    Statement  statement;
    Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
    public PlayersTopGames() throws SQLException {
        players = new ArrayList<>();

    }
    private List<Player> loadPlayers() throws SQLException {
        List<Player> loadedPlayers = new ArrayList<>();
        try(Connection conn = DefaultDataBaseConnectionPool.create().getConnection()){
            if(conn == null || conn.isClosed()){
                loadedPlayers.add(new Player("NULL0", 0, "NULL"));
                loadedPlayers.add(new Player("NULL1", 0, "NULL"));
                loadedPlayers.add(new Player("NULL2", 0, "NULL"));
                return loadedPlayers;
            }
            String sql = "SELECT * from players";
            try(Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql))
            {
                while(rs.next()){
                    int score = rs.getInt("score");
                    String name = rs.getString("name");
                    String state = rs.getString("result");
                    loadedPlayers.add(new Player(name, score, state));
                }
            }
        }catch(SQLException e){
            loadedPlayers.add(new Player("NULL0", 0, "NULL"));
            logger.error("Error log message", e);
        }
        return loadedPlayers;
    }
    public List<TopPlayersClass> loadTopPlayers() throws SQLException {
        players = loadPlayers();
        List<TopPlayersClass> playersTopGames = new ArrayList<>();
        for(Player player : players)
        {
            if(player.getState().equals("WON")||player.getState().equals("NULL"))
            {
               boolean exist = false;
               TopPlayersClass foundPlayer = null;
               for(var plg : playersTopGames)
               {
                   if(plg.getPlayerName().equals(player.getName())) {
                       foundPlayer = plg;
                       exist = true;
                   }
               }
               if(!exist)
               {
                   TopPlayersClass topPlayersClass = new TopPlayersClass(player.getName(), 1);
                   playersTopGames.add(topPlayersClass);
               }
               else {
                   foundPlayer.setTotalGames(foundPlayer.getTotalGames()+1);
               }
            }
        }
        playersTopGames.sort((o1, o2) -> Integer.compare(o2.getTotalGames(), o1.getTotalGames()));
        return playersTopGames;
    }

}