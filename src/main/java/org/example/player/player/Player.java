package org.example.player.player;
import org.example.game.gameInstance.GameTypes;
public class Player {

    private String name;
    private int score;

    public Player(String playerName) {
        this.name = playerName;
    }

    public static String getPlayerName() {
        {
            System.out.print("Please enter your name: ");

            return GameTypes.input.nextLine().trim();
        }
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
