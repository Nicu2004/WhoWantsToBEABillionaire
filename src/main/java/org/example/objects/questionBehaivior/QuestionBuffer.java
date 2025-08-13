package org.example.objects.questionBehaivior;

import org.example.utils.filework.LoadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionBuffer {

    public List<Question> loadQuestions() {
        final String fileName = LoadFile.loadFile();
        assert fileName != null;
        File questionsFile = new File(fileName);
        Scanner reader;
        try {
            reader = new Scanner(questionsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return questionReader(reader);
    }

    public  List<Question> questionReader(Scanner reader) {
        List<Question> questions = new ArrayList<>();
        while (reader.hasNextLine()) {
            // read question line number and question text
            String questionLine = reader.nextLine();
            Scanner lineScanner = new Scanner(questionLine);
            int questionNumber = lineScanner.nextInt();
            String questionText = lineScanner.hasNext() ? lineScanner.nextLine().trim() : "";
            lineScanner.close();

            String answerLine = reader.nextLine();

            var answers = answerReader(answerLine);

            int correctAnswer = Integer.parseInt(reader.nextLine().trim());
            Question q = new Question(questionNumber, questionText, answers, correctAnswer);
            questions.add(q);
        }
        return questions;
    }

    public  List<String> answerReader(String answerLine) {
        List<String> answers = new ArrayList<>(4);
        Scanner answerLineScanner = new Scanner(answerLine);

        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(answerLine);
        while (m.find()) {
            answers.add(m.group(1)); // text inside parentheses
        }
        answerLineScanner.close();
        return answers;
    }
}
