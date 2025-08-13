package org.example.objects.questionBehaivior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionEngine {

    private final QuestionHandler questionHandler;
    public QuestionEngine(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
    }
    public int iterateQuestion(List<Question> questionList, int score)
    {
        for(Question q: questionList)
        {

            if(questionHandler.checkAnswer(q, true))
            {
                score++;
                System.out.println("Correct! your score is "+score);
            }
            else {
                System.out.println("Incorrect");
                return score;
            }
        }
         return score;
    }
    public boolean iterateQuestion(List<Question> questionList)
    {
        for(Question q: questionList)
        {

            if(questionHandler.checkAnswer(q, true))
            {
                System.out.println("Correct!");
            }
            else {
                System.out.println("Incorrect");
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
