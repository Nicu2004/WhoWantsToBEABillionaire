import java.io.FileNotFoundException;
import java.util.*;

public class GameTypes {
    static Scanner input = new Scanner(System.in);
    public static void userGame(List<Question> questions) throws FileNotFoundException {

        Collections.shuffle(questions);
        System.out.println(ConsoleColors.RED+"Please enter the answer, a number between 1 and 4\n"+ConsoleColors.RESET);
        int i =1;
        Random rand = new Random();
        int randomScore = rand.nextInt(questions.size());
        for (var q: questions) {
            if(i == randomScore) {
                System.out.println("Congrats, you finished your game");
                break;
            }
            else {
                System.out.println(i + " " + q.question() + " " + q.answers()+"\n");
                if(Question.checkAnswer(q, false))
                    i++;
                else {
                    System.out.println(ConsoleColors.RED + "you want to try again?");

                        if (input.nextLine().equalsIgnoreCase("yes")) {
                            userGame(questions);
                        } else break;

                }
            }
        }
    }
    public static void playerGame(Player player, List<Question> questions) throws FileNotFoundException {

        int score = 0;
        List<Question> groupedQuestions = new ArrayList<>(5);
        int level = 1;
        for (int i = 0; i < questions.size(); i++) {
            groupedQuestions.add(questions.get(i));
            if ((i + 1) % 5 == 0) {
                System.out.println("Level " + level);
                int status = Printer.displayQuestions(groupedQuestions,score, player);
                level++;
                if (status == 0)
                    break;
                else if (status == 2) {
                    i = 0;
                    level = 1;
                    score =0;
                    groupedQuestions.clear();
                } else {
                    groupedQuestions.clear();
                }
            }
        }
    }
}
