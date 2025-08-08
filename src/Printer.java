import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Printer {

    public static void displayWelcomeMessage(boolean isPlayer, Player player) {
        if (isPlayer) {
            System.out.printf("Welcome %s to Who Wants To Be A Millionaire%n", player.name);
        } else {
            System.out.println("Welcome to game trial");
        }
    }

    public static void showQuestion(Question q) {
        System.out.println(ConsoleColors.RED+"Please enter the answer, a number between 1 and 4\n  or a to ask the audience, c to call a friend or f to make it 50% 50%"+ConsoleColors.RESET);
        System.out.println(q.questionId() + " " + q.question() + " " + q.answers()+"\n");
    }
    public static void showCorrectMessage(int score)
    {
        System.out.println(ConsoleColors.GREEN_BOLD+"Correct!"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN_BOLD+"Score: "+score);
    }
    public static void showRegretMessage(int score)
    {
        System.out.println("Your current score is " + score);
        System.out.println("Sorry you didn't become a millionaire\n");
    }
    public static int displayQuestions(List<Question> questions, int score, Player player) {
        Collections.shuffle(questions);
        Random rand = new Random();
        int index = rand.nextInt(questions.size());
        Question q = questions.get(index);
        Printer.showQuestion(q);
        if (Question.checkAnswer(q, true)) {
            score++;
            Printer.showCorrectMessage(score);
            if (score == 5) {
                System.out.printf(ConsoleColors.GREEN_BOLD_BRIGHT + "Hooray, %s you have become a millionaire" + ConsoleColors.RESET, player.name);
                return 0;
            }
            return 1;
        } else {
            Printer.showRegretMessage(score);
            return 0;
        }

    }
}
