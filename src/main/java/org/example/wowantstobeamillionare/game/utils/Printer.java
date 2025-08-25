package org.example.wowantstobeamillionare.game.utils;

import org.example.wowantstobeamillionare.controllers.GameUIController;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.player.player.Player;

public class Printer {

    public static void displayWelcomeMessage(boolean isPlayer, Player player) {
        GameUIController.getInstance().displayWelcomeMessage(isPlayer, player);
    }

    public static void showQuestion(Question q) {

        GameUIController.getInstance().setQuestion(q);
    }

//    public  void showCorrectMessage(int score) {
//       messageDisplayer.showCorrectAnswer(score);
//    }

    public static void showRegretMessage(int score) {
        GameUIController.getInstance().showRegretMessage(score);
    }


    public static void showWinnerMessage(Player player) {
        GameUIController.getInstance().showWinnerMessage(player.getScore());
    }

    public static void interractiveMessageForUser(Question q) {
       GameUIController.getInstance().interractiveMessageForUser(q);
    }
}
