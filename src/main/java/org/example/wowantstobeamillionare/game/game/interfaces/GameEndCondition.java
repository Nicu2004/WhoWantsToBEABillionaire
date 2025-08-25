package org.example.wowantstobeamillionare.game.game.interfaces;

public interface GameEndCondition {
    boolean shouldContinue(boolean lastGameResult, int gamesPlayed);
}
