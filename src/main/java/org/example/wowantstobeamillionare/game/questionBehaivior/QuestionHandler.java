package org.example.wowantstobeamillionare.game.controllers.questionBehaivior;

import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
public class QuestionHandler {

    public static boolean answerSwitch(Question q, int selectedChoice) {

        return switch (selectedChoice) {
            case 0, 1, 2, 3 -> selectedChoice == q.getCorrectAnswer();
            default -> throw new IllegalStateException("Unexpected value: " + selectedChoice);
        };
    }
}
