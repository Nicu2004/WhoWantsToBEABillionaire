package org.example.wowantstobeamillionare.game.controllers.questionEngine;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;


public interface AnswerValidatorService {
    boolean isCorrect(Button answer, Question question, Text status);
}
