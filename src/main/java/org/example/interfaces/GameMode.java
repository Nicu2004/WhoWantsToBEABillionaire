package org.example.interfaces;

import org.example.objects.player.Player;
import org.example.objects.questionBehaivior.Question;

import java.util.List;

public interface GameMode {
    boolean playGame(List<Question> questions, Player player);
    boolean playGame(List<Question> questions);
}
