import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @param correctAnswer index as
 */
public record Question(int questionId, String question, List<String> answers, int correctAnswer) {

    private static final String fileName = LoadFile.loadFile();


    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        File questionsFile = new File(fileName);
        Scanner reader;
        try {
            reader = new Scanner(questionsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (reader.hasNextLine()) {
            // read question line number and question text
            String questionLine = reader.nextLine();
            Scanner lineScanner = new Scanner(questionLine);
            int questionNumber = lineScanner.nextInt();
            String questionText = lineScanner.hasNext() ? lineScanner.nextLine().trim() : "";
            lineScanner.close();

            //read the answers
            String answerLine = reader.nextLine();
            List<String> answers = new ArrayList<>(4);
            Scanner answerLineScanner = new Scanner(answerLine);

            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(answerLine);
            while (m.find()) {
                answers.add(m.group(1)); // text inside parentheses
            }
            answerLineScanner.close();
            int correctAnswer = Integer.parseInt(reader.nextLine().trim());
            Question q = new Question(questionNumber, questionText, answers, correctAnswer);
            questions.add(q);
        }

        return questions;
    }
    static boolean checkAnswer(Question q, boolean isPlayer) {

        while (true) {
            // Show the question first
            System.out.println(q.toString());
            System.out.print("Your choice: ");

            String answer = GameTypes.input.nextLine().trim();

            switch (answer) {
                case "a" -> {
                    if (!isPlayer) {
                        System.out.println("Sorry, available only for players");
                    } else {
                        Helper.askAudience(q);
                    }
                }
                case "c" -> {
                    if (!isPlayer) {
                        System.out.println("Sorry, available only for players");
                    } else {
                        System.out.printf("I think it is %d, but I'm not 100%% sure%n", Helper.callFriend(q));
                    }
                }
                case "f" -> {
                    if (!isPlayer) {
                        System.out.println("Sorry, available only for players");
                    } else {
                        int option = Helper.make50_50(q);
                        System.out.println("Choose 1 or 2:");
                        try {
                            int userChoice = Integer.parseInt(GameTypes.input.nextLine().trim());
                            return userChoice == option;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number");
                        }
                    }
                }
                case "1", "2", "3", "4" -> {
                    int option = Integer.parseInt(answer) - 1;
                    return option == q.correctAnswer();
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
