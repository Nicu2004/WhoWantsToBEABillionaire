package org.example.inGameModes;

import org.example.inGameModes.Modes.AskAudience;
import org.example.inGameModes.Modes.CallFriend;
import org.example.inGameModes.Modes.FiftyFifty;
import org.example.inGameModes.Modes.LifeLine;
import org.example.objects.questionBehaivior.Question;
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
