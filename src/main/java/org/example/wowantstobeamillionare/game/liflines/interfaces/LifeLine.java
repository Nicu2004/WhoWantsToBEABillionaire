package org.example.wowantstobeamillionare.game.inGameModes.Modes.liflines.interfaces;

import org.example.wowantstobeamillionare.game.controllers.player.playerBehavior.PlayerGameScene;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

public interface LifeLine {

    void execute(Question question, PlayerGameScene  playerGameScene);
}


