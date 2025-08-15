package org.example.game.gameInstance;

import org.example.player.player.Player;
import org.example.questions.questionBehaivior.Question;
import org.example.questions.questionBehaivior.QuestionEngine;
import org.example.questions.questionBehaivior.QuestionHandler;
import org.example.utils.filework.FileWriter;
import org.example.utils.Printer;

import java.util.*;


public class GameTypes {

    public static Scanner input = new Scanner(System.in);
    QuestionHandler questionHandler = new QuestionHandler(input);
    final QuestionEngine questionEngine = new QuestionEngine(questionHandler);

    public GameTypes(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
    }

    public boolean userGame(List<Question> questions) {

        Collections.shuffle(questions);
        return questionEngine.iterateQuestion(questions, false);
    }

    public boolean playerGame(List<Question> questions, Player player) {

        int score = 0;
        List<Question> groupedQuestions = questionEngine.groupQuestions(questions);
        score = questionEngine.iterateQuestion(groupedQuestions, score, player) ;
        if(score == 5)
            Printer.showWinnerMessage(player);
        else
            Printer.showRegretMessage(score);
        FileWriter fw = new FileWriter(player.getName(),score);
        fw.appendToFile();
        return false;
    }
}
