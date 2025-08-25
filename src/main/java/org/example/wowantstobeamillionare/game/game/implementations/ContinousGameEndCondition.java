package org.example.wowantstobeamillionare.game.game.implementations;

import org.example.wowantstobeamillionare.game.game.interfaces.GameEndCondition;

public class ContinousGameEndCondition implements GameEndCondition {
    @Override
    public boolean shouldContinue(boolean lastGameResult, int gamesPlayed) {
        return lastGameResult;
    }
}
