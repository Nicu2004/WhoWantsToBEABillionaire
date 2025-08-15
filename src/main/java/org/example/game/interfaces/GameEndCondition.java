package org.example.game.interfaces;

public interface GameEndCondition {
    boolean shouldContinue(boolean lastGameResult, int gamesPlayed);
}
