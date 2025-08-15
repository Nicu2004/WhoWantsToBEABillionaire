package org.example.player.player;

public record PlayerResults(int score, String result) {

    @Override
    public String toString() {
        return "Score: " + score + ", Result: " + result;
    }
}
