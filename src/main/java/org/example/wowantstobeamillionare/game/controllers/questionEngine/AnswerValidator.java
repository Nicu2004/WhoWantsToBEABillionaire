package org.example.wowantstobeamillionare.game.controllers.questionEngine;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionHandler;

import java.util.Objects;

public class AnswerValidator implements AnswerValidatorService {

    @Override
    public boolean isCorrect(Button answer, Question question, Text status) {
        int selectedOption =0 ;
        if(Objects.equals(answer.getId(), "ans2"))
            selectedOption = 1;
        if(Objects.equals(answer.getId(), "ans3"))
            selectedOption = 2;
        if (Objects.equals(answer.getId(), "ans4"))
            selectedOption = 3;
        return QuestionHandler.answerSwitch(question, selectedOption);
    }
    public int answerHandlerService(Button answer, Question question, Text status,Player player, int currentIndex) {
        if(isCorrect(answer, question, status))
        {
            int score = player.getScore();
            status.setText("Correct");
            status.setFill(Color.GREEN);
            status.setFont(Font.font("System", FontWeight.BOLD, 20));
            score++;
            System.out.println("Current score is "+ score);
            player.setScore(score);
            currentIndex++;
            return currentIndex;
        }
        else
        {
            status.setText("Incorrect");
            status.setFill(Color.RED);
            status.setFont(Font.font("System", FontWeight.BOLD, 20));
            return -1;
        }
    }
}
