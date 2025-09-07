package org.example.wowantstobeamillionare.game.controllers.finalControllers.classses;



public class PlayerGameResults {

    private final String playerName;
    private final int score;
    private final GameStatus status;

    public PlayerGameResults(String playerName, int score, GameStatus status)
    {
        this.playerName = playerName;
        this.score = score;
        this.status = status;
    }
    public String getPlayerName()
    {
        return playerName;
    }
    public int getScore()
    {
        return score;
    }
    public GameStatus getStatus()
    {
        return status;
    }

    public enum GameStatus {
        WON, LOST;

        public static GameStatus fromScore(int score)
        {
            return score==5?WON:LOST;
        }
    }


}
