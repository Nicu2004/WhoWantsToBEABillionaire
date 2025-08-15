package org.example.game.interfaces;

import org.example.player.player.Player;
import org.example.questions.questionBehaivior.Question;

import java.util.List;

public interface GameMode {
    boolean playGame(List<Question> questions, Player player);
    boolean playGame(List<Question> questions);
}
