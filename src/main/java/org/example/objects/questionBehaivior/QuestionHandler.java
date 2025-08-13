package org.example.objects.questionBehaivior;

import org.example.inGameModes.Helper;
import org.example.inGameModes.Modes.FiftyFifty;
import org.example.utils.Printer;

import java.util.Scanner;

public class QuestionHandler {

        private final Scanner inputReader;

        public QuestionHandler(Scanner inputReader)
        {

            this.inputReader = inputReader;
        }
        public boolean checkAnswer(Question q, boolean isPlayer) {
        Printer.showQuestion(q);
        System.out.print("Your choice: ");
        return answerSwitch(q, isPlayer);
    }

    public boolean isCorrectAnswer(Question q, int choice) {
            return choice==q.getCorrectAnswer();
    }

    public boolean answerSwitch(Question q, boolean isPlayer) {
        while(true)
        {
            String answer = inputReader.nextLine().trim();
            switch (answer) {
                case "a" -> {
                    if (!isPlayer) {
                        System.out.println("Sorry, available only for players");
                    } else {
                        Helper.useLifeLIne("audience", q);
                        int choice = readUserChoice();
                        return isCorrectAnswer(q, choice);
                    }
                }
                case "c" -> {
                    if (!isPlayer) {
                        System.out.println("Sorry, available only for players");
                    } else {

                        Helper.useLifeLIne("callFriend", q);
                        int choice = readUserChoice();
                        return isCorrectAnswer(q, choice);
                    }
                }
                case "f" -> {
                    if (!isPlayer) {
                        System.out.println("Sorry, available only for players");
                    } else {
                        Helper.useLifeLIne("fiftyFifty", q);
                        FiftyFifty fiftyFifty = new FiftyFifty();
                        fiftyFifty.execute(q);
                        Question newQ = fiftyFifty.getLastReducedQuestion();
                        System.out.println("Choose 1 or 2:");
                        int choice = readUserChoice();
                        return isCorrectAnswer(newQ, choice);
                    }
                }
                case "1", "2", "3", "4" -> {
                    int option = Integer.parseInt(answer) - 1;
                    return option == q.getCorrectAnswer();
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
    private int readUserChoice() {
            try
            {
                return Integer.parseInt(inputReader.nextLine())-1;
            }catch(NumberFormatException e)
            {
                System.out.println("Invalid number");
                return readUserChoice();
            }
    }
}
