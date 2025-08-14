package org.example.utils;

import org.example.objects.questionBehaivior.Question;
import org.example.objects.player.Player;


public class Printer {

    public static void displayWelcomeMessage(boolean isPlayer, Player player) {
        if (isPlayer) {
            System.out.printf("Welcome %s to Who Wants To Be A Millionaire%n", player.getName());
        } else {
            System.out.println("Welcome to game trial");
        }
    }

    public static void showQuestion(Question q) {

        System.out.println(ConsoleColors.RED + "Please enter the answer, a number between 1 and 4\n  or a to ask the audience, c to call a friend or f to make it 50% 50%" + ConsoleColors.RESET);
        System.out.println(q.getQuestion() + " " + q.getAnswers() + "\n");
    }

    public static void showCorrectMessage(int score) {
        System.out.println(ConsoleColors.GREEN_BOLD + "Correct!" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN_BOLD + "Score: " + score + ConsoleColors.RESET);
    }

    public static void showRegretMessage(int score) {
        System.out.println("Your current score is " + score);
        System.out.println("Sorry you didn't become a millionaire\n");
    }

    public static void showWinnerMessage(Player player) {
        System.out.printf(ConsoleColors.GREEN_BOLD_BRIGHT + "Hooray, %s you have become a millionaire\n" + ConsoleColors.RESET, player.getName());
    }

    public static void interractiveMessageForUser(Question q) {
        System.out.println(ConsoleColors.RED + "Please enter the answer, a number between 1 and 4\n" + ConsoleColors.RESET);
        System.out.println(q.getQuestion() + " " + q.getAnswers() + "\n");
    }


}
