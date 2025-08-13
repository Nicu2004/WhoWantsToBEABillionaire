package org.example.implementations;

import org.example.gameInstance.GameTypes;
import org.example.interfaces.GameMode;
import org.example.objects.player.Player;
import org.example.objects.questionBehaivior.Question;
import org.example.objects.questionBehaivior.QuestionHandler;

import java.util.List;

public class StandardGameMode implements GameMode {
    private final GameTypes gameTypes;
    public StandardGameMode(GameTypes gameTypes) {
        this.gameTypes = new GameTypes(new QuestionHandler(GameTypes.input));
    }

    @Override
    public boolean playGame(List<Question> questions, Player player) {
        return gameTypes.playerGame(questions, player);
    }

    @Override
    public boolean playGame(List<Question> questions) {
        return gameTypes.userGame(questions);
    }
}
