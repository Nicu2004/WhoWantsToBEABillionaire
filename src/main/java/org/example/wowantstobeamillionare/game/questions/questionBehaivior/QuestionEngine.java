package org.example.wowantstobeamillionare.game.questions.questionBehaivior;

import org.example.wowantstobeamillionare.controllers.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.game.player.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.wowantstobeamillionare.game.player.implementations.DefaultPlayerService;

public class QuestionEngine {
    private final DefaultPlayerService playerService;
    private final QuestionHandler questionHandler;
    public QuestionEngine(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
        this.playerService = new DefaultPlayerService();
    }
    // for players

    public ArrayList<Question> groupQuestions(List<Question> questionList) {
        Random random = new Random();
        ArrayList<Question> groupedQuestions = new ArrayList<>();
        int groupSize = 5;
        int size = questionList.size();
        for (int start = 0; start < size; start += groupSize) {
            int end = Math.min(start + groupSize - 1, size - 1);
            int randomIndex = random.nextInt(end - start + 1) + start;
            groupedQuestions.add(questionList.get(randomIndex));
        }
        return groupedQuestions;
    }
}
