package org.example.wowantstobeamillionare.game.game.gameInstance;

import org.example.wowantstobeamillionare.game.player.player.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionEngine;
import org.example.wowantstobeamillionare.controllers.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.game.utils.filework.FileWriter;
import org.example.wowantstobeamillionare.game.utils.Printer;

import java.util.*;


public class GameTypes {
    String input;
    QuestionHandler questionHandler = new QuestionHandler();
    final QuestionEngine questionEngine = new QuestionEngine(questionHandler);

    public GameTypes(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
    }

    public boolean userGame(List<Question> questions) {

        Collections.shuffle(questions);
        return questions.stream().anyMatch(question -> question.getQuestion().equals(question.getQuestion()));
    }
    public boolean playerGame(List<Question> questions, Player player) {

        int score = 0;
        List<Question> groupedQuestions = questionEngine.groupQuestions(questions);
        if(score == 5)
            Printer.showWinnerMessage(player);
        else
            Printer.showRegretMessage(score);
        FileWriter fw = new FileWriter(player.getName(),score);
        fw.appendToFile();
        return false;
    }
}
