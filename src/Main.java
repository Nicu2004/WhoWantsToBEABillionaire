import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "questions.txt";
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
                System.out.println("File will be created at: " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
            List<Question> questions = new ArrayList<>();
            File questionsFile = new File(fileName);
            Scanner reader = new Scanner(questionsFile);

            while (reader.hasNextLine()) {
                // read question line number and question text
               String questionLine = reader.nextLine();
               Scanner lineScanner= new Scanner(questionLine);
               int questionNumber = lineScanner.nextInt();
               String questiontext = lineScanner.hasNext()? lineScanner.nextLine().trim() : "";
               lineScanner.close();

               //read the answers
                String answerLine = reader.nextLine();
                List<String> answers = new ArrayList<>(3);
                Scanner answerLineScanner = new Scanner(answerLine);


                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(answerLine);
                while (m.find()) {
                    answers.add(m.group(1)); // text inside parentheses
                }
                answerLineScanner.close();
                int correctAnswer = Integer.parseInt(reader.nextLine().trim());
                Question q = new Question(questionNumber, questiontext, answers, correctAnswer);
                questions.add(q);
            }
            reader.close();

            List<Question>groupedQuestions = new ArrayList<>(6);
            int level = 1;
            for(int i=0; i<questions.size();i++) {
                groupedQuestions.add(questions.get(i));
                if((i+1)%5==0)
                {
                    System.out.println("Level " + level);
                    DisplayQuestions(groupedQuestions);
                    level++;
                    System.out.println();
                    groupedQuestions.clear();
                }

            }
            if(!groupedQuestions.isEmpty()) {
                System.out.println("Level " + level);
                DisplayQuestions(groupedQuestions);

            }
    }

    private static void DisplayQuestions(List<Question> questions) {
            for(var q: questions)
            {
                System.out.println(q.getQuestionId() + " "+ q.getQuestion() + " "+q.getAnswers() + " " + q.getCorrectAnswer());
            }

    }

}