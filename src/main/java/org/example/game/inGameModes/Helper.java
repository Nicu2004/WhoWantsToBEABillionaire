package org.example.game.inGameModes;

import org.example.game.inGameModes.Modes.liflines.implementations.AskAudience;
import org.example.game.inGameModes.Modes.liflines.implementations.CallFriend;
import org.example.game.inGameModes.Modes.liflines.implementations.FiftyFifty;
import org.example.game.inGameModes.Modes.liflines.interfaces.LifeLine;
import org.example.questions.questionBehaivior.Question;
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

    public static void useLifeLIne(String name, Question question)
    {
        LifeLine l = lifeLines.get(name);
        if(l != null)
        {
            l.execute(question);
        }else {
            System.out.println("The life line " + name + " does not exist.");
        }
    }

}
