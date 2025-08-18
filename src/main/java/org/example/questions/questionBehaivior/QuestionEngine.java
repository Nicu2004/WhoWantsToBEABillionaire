package org.example.questions.questionBehaivior;

import org.example.player.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.player.implementations.DefaultPlayerService;

public class QuestionEngine {
    private final DefaultPlayerService playerService;
    private final QuestionHandler questionHandler;
    public QuestionEngine(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
        this.playerService = new DefaultPlayerService();
    }
    public int iterateQuestion(List<Question> questionList, int score, Player player)
    {
        for(Question q: questionList)
        {

            if(questionHandler.checkAnswer(q, true))
            {
                score++;
                playerService.updatePlayerScore(player, score);
                System.out.println("Correct! your score is "+score);
            }
            else {
                System.out.println("Incorrect");
                return score;
            }
        }
         return score;
    }
    public boolean iterateQuestion(List<Question> questionList, boolean isPlayer)
    {
        int size = 0;
        for(Question q: questionList)
        {
            if(questionHandler.checkAnswer(q, isPlayer))
            {
                System.out.println("Correct!");
            }
            else {
                System.out.println("Incorrect");
                return false;
            }
            size++;
            if(size == questionList.size())
            {
                return false;
            }
        }
        return true;
    }

    public List<Question> groupQuestions(List<Question> questionList) {
        Random random = new Random();
        List<Question> groupedQuestions = new ArrayList<>();
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
