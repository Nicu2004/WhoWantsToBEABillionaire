package org.example.wowantstobeamillionare.game.liflines.implementations;

import javafx.scene.control.Alert;
import org.example.wowantstobeamillionare.game.controllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.liflines.interfaces.LifeLine;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import java.util.Random;

public class CallFriend implements LifeLine {

    private int getWrongAnswer(Question question, Random rand) {
        int wrong;
        do {
            wrong = rand.nextInt(question.getAnswers().size()-1);
        }while(wrong== question.getCorrectAnswer());
        return wrong;
    }

    @Override
    public void execute(Question question, GameEngineSceneController gameEngineSceneController) {
        Random rand = new Random();
        boolean correct = rand.nextInt(100)<90;
        int answerIndex = correct?question.getCorrectAnswer():getWrongAnswer(question, rand);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Life Line Call a Friend");
        alert.setContentText("I think it is "+ question.getAnswers().get(answerIndex));
        alert.showAndWait();
    }
}
