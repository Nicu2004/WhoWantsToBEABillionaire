package org.example.questions.questionBehaivior;

import org.example.utils.filework.LoadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.database.databaseConnection.getConn;

public class QuestionBuffer {

    public List<Question> loadQuestions() throws SQLException {

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

    public  List<Question> questionReader(Scanner reader) throws SQLException {
        List<Question> questions = new ArrayList<>();
        var conn = getConn();
        String sql =  "SELECT question_order, question, ans1, ans2, ans3, ans4, corect_answer FROM questions";
        try(Statement st = conn.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql)){
            while (rs.next()) {
                int id = rs.getInt("question_order");
                String questionText = rs.getString("question");
                String firstAnswer =  rs.getString("ans1");
                String secondAnswer = rs.getString("ans2");
                String thirdAnswer =  rs.getString("ans3");
                String fourthAnswer =  rs.getString("ans4");
                int correctAnswer =   rs.getInt("corect_answer");
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
//    public  List<Question> questionReader(Scanner reader) {
//        List<Question> questions = new ArrayList<>();
//        while (reader.hasNextLine()) {
//            // read question line number and question text
//            String questionLine = reader.nextLine();
//            Scanner lineScanner = new Scanner(questionLine);
//            int questionNumber = lineScanner.nextInt();
//            String questionText = lineScanner.hasNext() ? lineScanner.nextLine().trim() : "";
//            lineScanner.close();
//
//            String answerLine = reader.nextLine();
//
//            var answers = answerReader(answerLine);
//
//            int correctAnswer = Integer.parseInt(reader.nextLine().trim());
//            Question q = new Question(questionNumber, questionText, answers, correctAnswer);
//            questions.add(q);
//        }
//        return questions;
//    }
//
//    public  List<String> answerReader(String answerLine) {
//        List<String> answers = new ArrayList<>(4);
//        Scanner answerLineScanner = new Scanner(answerLine);
//
//        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(answerLine);
//        while (m.find()) {
//            answers.add(m.group(1)); // text inside parentheses
//        }
//        answerLineScanner.close();
//        return answers;
//    }

}
