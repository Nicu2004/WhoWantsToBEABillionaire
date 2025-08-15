package org.example.game.inGameModes.Modes.liflines.implementations;

import org.example.game.inGameModes.Modes.liflines.interfaces.LifeLine;
import org.example.game.inGameModes.PercentageDistribution;
import org.example.questions.questionBehaivior.Question;
import java.util.List;

public class AskAudience implements LifeLine {
    @Override
    public void execute(Question question) {
        {
            System.out.println("Asking audience for the answer...");
            int[] audienceResponse =  PercentageDistribution.generatePercentages();
            distributeAudienceCorrectAnswer(audienceResponse,question.getCorrectAnswer(), question.getAnswers());//the audience is asked and a String is shows=d with the results
        }
    }
    private static void distributeAudienceCorrectAnswer(int[] newAudience, int correctIndex, List<String> questions)//here we have the probabilities, and the bigger prob is inserted at the correct index from the answer
    {
        int maxIndex = 0;
        for (int j = 1; j < newAudience.length; j++) {
            if (newAudience[j] > newAudience[maxIndex]) {
                maxIndex = j;//check for the biggest probability
            }
        }
        var tmp = newAudience[maxIndex];
        newAudience[maxIndex] = newAudience[correctIndex];
        //swap here
        newAudience[correctIndex] = tmp;

        StringBuilder st = new StringBuilder("The audience results are: ");
        for (int i = 0; i < newAudience.length; i++) {
            st.append(questions.get(i)).append(" ").append(newAudience[i]).append("% ");//insert the answers and their corresponding probabilities
        }
        System.out.println(st);
    }
}
