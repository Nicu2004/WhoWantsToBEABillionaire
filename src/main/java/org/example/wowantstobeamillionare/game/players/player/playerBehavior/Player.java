package org.example.wowantstobeamillionare.game.players.player.playerBehavior;

public class Player {

    private String name;
    private int score;
    private final String state;
    public Player(String playerName) {
        name = playerName;
        score = 0;
        state = "default";
    }
    public Player(String playerName, int score, String state) {
        name = playerName;
        this.score = score;
        this.state = state;
    }
    public Player getPlayer() {
        return this;
    }

    public String getName() {

        return name;
    }
    public String getState() {
        return state;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
