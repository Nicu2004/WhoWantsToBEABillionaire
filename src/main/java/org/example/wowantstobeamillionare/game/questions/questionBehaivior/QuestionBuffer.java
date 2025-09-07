package org.example.wowantstobeamillionare.game.questions.questionBehaivior;

import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnection;
import org.example.wowantstobeamillionare.game.database.PgStatemantClass;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionBuffer {

    File readFile = new File("questions.txt");

    public QuestionBuffer() {
    }

    public ArrayList<Question> loadQuestions() throws SQLException {

        return questionReader();
    }
    public ArrayList<Question> questionReader() throws SQLException {
        ArrayList<Question> questions =  new ArrayList<>();
        if (DefaultDataBaseConnection.getConn() == null) {
            System.out.println("Connection is null so the question wont load");
            try{
                Scanner reader =new Scanner(readFile);
                questions = questionReaderFromFile(reader);
            }catch(Exception e){
                System.out.println("Error reading file");
            }
        } else {
            questions =loadQuestionsFromDatabase();
        }
        System.out.println(questions.getFirst().getQuestion());
        return questions;
    }
    public ArrayList<Question> loadQuestionsFromDatabase() throws SQLException {
        Statement statement = PgStatemantClass.createStmt();
        ArrayList<Question> questions = new ArrayList<>();
        System.out.println("Connection is " + DefaultDataBaseConnection.getConn());
        String sql = "SELECT question_order, question, ans1, ans2, ans3, ans4, correct_answer FROM questions";
        try (
                ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("question_order");
                String questionText = rs.getString("question");
                String firstAnswer = rs.getString("ans1");
                String secondAnswer = rs.getString("ans2");
                String thirdAnswer = rs.getString("ans3");
                String fourthAnswer = rs.getString("ans4");
                int correctAnswer = rs.getInt("correct_answer");
                List<String> answers = new ArrayList<>();
                answers.add(firstAnswer);
                answers.add(secondAnswer);
                answers.add(thirdAnswer);
                answers.add(fourthAnswer);
                questions.add(new Question(id, questionText, answers, correctAnswer));
            }
        }
        return questions;
    }
    public ArrayList<Question> questionReaderFromFile (Scanner reader){
        ArrayList<Question> questions = new ArrayList<>();
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

    public List<String> answerReader (String answerLine){
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
