package org.example.wowantstobeamillionare.game.liflines.implementations;
import org.example.wowantstobeamillionare.game.controllers.gameEngine.GameEngineSceneController;
import org.example.wowantstobeamillionare.game.liflines.interfaces.LifeLine;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class FiftyFifty implements LifeLine {


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
                gameEngineSceneController.disableSpecificButton(1);
                System.out.println("Button 1 disabled");
                disabledButtons.add(0);
            }
            else if(randomNumber == 1) {
                gameEngineSceneController.disableSpecificButton(2);
                System.out.println("Button 2 disabled");
                disabledButtons.add(1);
            }
            else if(randomNumber == 2) {
                gameEngineSceneController.disableSpecificButton(3);
                System.out.println("Button 3 disabled");
                disabledButtons.add(2);
            }
            else {
                gameEngineSceneController.disableSpecificButton(4);
                System.out.println("Button 4 disabled");
                disabledButtons.add(3);
            }
            numberToBeDesabled++;
        }
    }
}

