package org.example.wowantstobeamillionare.game.liflines.interfaces;

import org.example.wowantstobeamillionare.game.controllers.sceneControllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

public interface LifeLine {

    void execute(Question question, GameEngineSceneController gameEngineSceneController);
}


