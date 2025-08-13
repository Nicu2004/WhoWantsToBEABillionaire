package org.example.implementations;

import org.example.interfaces.GameEndCondition;

public class ContinousGameEndCondition implements GameEndCondition {
    @Override
    public boolean shouldContinue(boolean lastGameResult, int gamesPlayed) {
        return lastGameResult;
    }
}
