package org.example.wowantstobeamillionare.game.addon;

public class TopPlayersClass
{
    private String playerName;
    private int totalGames;

    public  TopPlayersClass(String name, int gamesPlayed)
    {
        playerName = name;
        totalGames = gamesPlayed;
    }

    public TopPlayersClass() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }
}
