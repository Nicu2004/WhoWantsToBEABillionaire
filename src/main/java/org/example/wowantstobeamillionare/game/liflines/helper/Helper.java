package org.example.wowantstobeamillionare.game.liflines.helper;

import org.example.wowantstobeamillionare.game.controllers.sceneControllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.liflines.implementations.AskAudience;
import org.example.wowantstobeamillionare.game.liflines.implementations.CallFriend;
import org.example.wowantstobeamillionare.game.liflines.implementations.FiftyFifty;
import org.example.wowantstobeamillionare.game.liflines.interfaces.LifeLine;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    private static final Map<String, LifeLine> lifeLines = new HashMap<>();

    static
    {
        lifeLines.put("audience", new AskAudience());
        lifeLines.put("callFriend", new CallFriend());
        lifeLines.put("fiftyFifty", new FiftyFifty());
    }

    public static void useLifeLIne(String name, Question question, GameEngineSceneController gameEngineSceneController)
    {
        LifeLine l = lifeLines.get(name);
        if(l != null)
        {
            l.execute(question, gameEngineSceneController);
        }else {
            System.out.println("The life line " + name + " does not exist.");
        }
    }

}
