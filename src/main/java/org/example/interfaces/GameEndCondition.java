package org.example.interfaces;

public interface GameEndCondition {
    boolean shouldContinue(boolean lastGameResult, int gamesPlayed);
}
