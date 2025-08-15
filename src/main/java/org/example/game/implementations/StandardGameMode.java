package org.example.game.implementations;

import org.example.game.gameInstance.GameTypes;
import org.example.game.interfaces.GameMode;
import org.example.player.player.Player;
import org.example.questions.questionBehaivior.Question;
import org.example.questions.questionBehaivior.QuestionHandler;

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
