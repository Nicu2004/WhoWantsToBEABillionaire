package org.example.wowantstobeamillionare.game.controllers.questionEngine;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

public interface QuestionService {
    void showCurrentQuestion(int currentIndex,TextField questionField, Button ans1, Button ans2, Button ans3, Button ans4);
}
