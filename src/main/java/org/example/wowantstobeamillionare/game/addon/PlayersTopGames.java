package org.example.wowantstobeamillionare.game.addon;

import org.example.wowantstobeamillionare.game.database.PgStatemantClass;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PlayersTopGames{
    Player player1, player2, player3;
    List<Player> players;
    Statement  statement;
    TopPlayersClass topPlayersClass;

    public PlayersTopGames() throws SQLException {
        players = new ArrayList<>();
        statement = PgStatemantClass.createStmt();
        topPlayersClass = new TopPlayersClass();
    }
    private List<Player> loadPlayers() throws SQLException {
        String sql  = "SELECT * FROM players";
        try(
                ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                int score = resultSet.getInt("score");
                String name = resultSet.getString("name");
                String state = resultSet.getString("result");
                players.add(new Player(name,  score, state));
            }
        }
        return players;
    }
    public List<TopPlayersClass> loadTopPlayers() throws SQLException {
        players = loadPlayers();
        List<TopPlayersClass> playersTopGames = new ArrayList<>();
        for(Player player : players)
        {
            if(player.getState().equals("WON"))
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
        System.out.println("Total Games: "+playersTopGames.size());
        playersTopGames.sort(new Comparator<TopPlayersClass>() {
            @Override
            public int compare(TopPlayersClass o1, TopPlayersClass o2) {
                return Integer.compare(o2.getTotalGames(), o1.getTotalGames());
            }
        });
        return playersTopGames;
    }

}