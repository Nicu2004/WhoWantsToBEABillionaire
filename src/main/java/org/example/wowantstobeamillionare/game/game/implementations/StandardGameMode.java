package org.example.wowantstobeamillionare.game.game.implementations;

import org.example.wowantstobeamillionare.game.game.gameInstance.GameTypes;
import org.example.wowantstobeamillionare.game.game.interfaces.GameMode;
import org.example.wowantstobeamillionare.game.player.player.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.controllers.questionBehaivior.QuestionHandler;

import java.util.List;

public class StandardGameMode implements GameMode {
    private final GameTypes gameTypes;
    public StandardGameMode(GameTypes gameTypes) {
        this.gameTypes = new GameTypes(new QuestionHandler());
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
