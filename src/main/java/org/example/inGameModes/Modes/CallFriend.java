package org.example.inGameModes.Modes;

import org.example.objects.questionBehaivior.Question;
import java.util.Random;

public class CallFriend implements LifeLine {
    @Override
    public void execute(Question question) {
        Random rand = new Random();
        boolean correct = rand.nextInt(100)<90;
        int answerIndex = correct?question.getCorrectAnswer():getWrongAnswer(question, rand);
        System.out.println("I think it is "+ question.getAnswers().get(answerIndex)+1);
    }
    private int getWrongAnswer(Question question, Random rand) {
        int wrong;
        do {
            wrong = rand.nextInt(question.getAnswers().size()-1);
        }while(wrong== question.getCorrectAnswer());
        return wrong;
    }
}
