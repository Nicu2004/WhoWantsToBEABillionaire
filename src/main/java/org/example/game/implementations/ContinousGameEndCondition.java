package org.example.game.implementations;

import org.example.game.interfaces.GameEndCondition;

public class ContinousGameEndCondition implements GameEndCondition {
    @Override
    public boolean shouldContinue(boolean lastGameResult, int gamesPlayed) {
        return lastGameResult;
    }
}
