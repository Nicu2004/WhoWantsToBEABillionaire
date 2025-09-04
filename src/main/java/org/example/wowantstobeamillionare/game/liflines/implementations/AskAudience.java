package org.example.wowantstobeamillionare.game.liflines.implementations;

import javafx.scene.control.Alert;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.liflines.interfaces.LifeLine;
import org.example.wowantstobeamillionare.game.addon.PercentageDistribution;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import java.util.List;

public class AskAudience implements LifeLine {

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

        StringBuilder st = new StringBuilder("The audience results are: \n");
        for (int i = 0; i < newAudience.length; i++) {
            st.append(questions.get(i)).append(" ").append(newAudience[i]).append("% \n");//insert the answers and their corresponding probabilities
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Life Line AskAudience");
        alert.setContentText(st.toString());
        alert.showAndWait();
    }

    @Override
    public void execute(Question question, GameEngineSceneController gameEngineSceneController) {
        int[] audienceResponse =  PercentageDistribution.generatePercentages();
        distributeAudienceCorrectAnswer(audienceResponse,question.getCorrectAnswer(), question.getAnswers());//the audience is asked and a String is shows=d with the results
    }
}
