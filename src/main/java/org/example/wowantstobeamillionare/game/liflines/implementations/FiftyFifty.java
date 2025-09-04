package org.example.wowantstobeamillionare.game.liflines.implementations;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.liflines.interfaces.LifeLine;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class FiftyFifty implements LifeLine {

    Question question;
    GameEngineSceneController gameEngineSceneController;
    public FiftyFifty() {}

    @Override
    public void execute(Question question, GameEngineSceneController gameEngineSceneController) {
        int numberToBeDesabled = 0;
        Set<Integer> disabledButtons = new HashSet<>();
        Random random = new Random();

        while(numberToBeDesabled < 2) {
            int randomNumber = random.nextInt(4); // Changed from 3 to 4 to include all buttons

            // Skip if this button was already disabled or if it's the correct answer
            if (disabledButtons.contains(randomNumber) || randomNumber == question.getCorrectAnswer()) {
                continue;
            }

            if(randomNumber == 0) {
                gameEngineSceneController.disableBtn1();
                disabledButtons.add(0);
            }
            else if(randomNumber == 1) {
                gameEngineSceneController.disableBtn2();
                disabledButtons.add(1);
            }
            else if(randomNumber == 2) {
                gameEngineSceneController.disableBtn3();
                disabledButtons.add(2);
            }
            else if(randomNumber == 3) {
                gameEngineSceneController.disableBtn4();
                disabledButtons.add(3);
            }

            numberToBeDesabled++;
        }
    }
}

