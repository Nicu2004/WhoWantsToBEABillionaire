package org.example.wowantstobeamillionare.game.controllers.finalControllers.classses;

public class TopPlayer {
    private final String playerName;
    private final int totalGames;
    public  TopPlayer(String playerName, int totalGames) {
        this.playerName = playerName;
        this.totalGames = totalGames;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getTotalGames() {
        return totalGames;
    }
}
