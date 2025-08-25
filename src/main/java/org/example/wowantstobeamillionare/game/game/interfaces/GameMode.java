package org.example.wowantstobeamillionare.game.game.interfaces;

import org.example.wowantstobeamillionare.game.player.player.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

import java.util.List;

public interface GameMode {
    boolean playGame(List<Question> questions, Player player);
    boolean playGame(List<Question> questions);
}
