package org.example.wowantstobeamillionare.controllers.questionBehaivior;

import org.example.wowantstobeamillionare.game.game.inGameModes.Helper;
import org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.implementations.FiftyFifty;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.utils.Printer;

public class QuestionHandler {

    public QuestionHandler() {
    }

    public boolean isCorrectAnswer(Question q, int choice) {
        return choice == q.getCorrectAnswer();
    }

    public boolean answerSwitch(Question q, String selectedChoice) {

        return switch (selectedChoice) {
            case "a" -> {
                Helper.useLifeLIne("audience", q);
                yield isCorrectAnswer(q, Integer.parseInt(selectedChoice));
            }
            case "c" -> {
                Helper.useLifeLIne("callFriend", q);
                yield isCorrectAnswer(q, Integer.parseInt(selectedChoice));
            }
            case "f" -> {
                Helper.useLifeLIne("fiftyFifty", q);
                FiftyFifty fiftyFifty = new FiftyFifty();
                fiftyFifty.execute(q);
                Question newQ = fiftyFifty.getLastReducedQuestion();
                yield isCorrectAnswer(newQ, Integer.parseInt(selectedChoice));
            }
            case "0", "1", "2", "3" -> Integer.parseInt(selectedChoice) == q.getCorrectAnswer();

            default -> throw new IllegalStateException("Unexpected value: " + selectedChoice);
        };
    }
}
