package org.example.wowantstobeamillionare.game.player.player;
public class Player {

    private String name;
    private int score;

    public Player(String playerName) {
        this.name = playerName;
        score = 0;
    }
    public Player getPlayer(){
        return this;
    }

    public String getName() {

        return name;
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
