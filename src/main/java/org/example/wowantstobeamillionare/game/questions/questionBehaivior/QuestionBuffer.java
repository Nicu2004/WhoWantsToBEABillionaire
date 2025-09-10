package org.example.wowantstobeamillionare.game.questions.questionBehaivior;

import org.example.wowantstobeamillionare.game.addon.DefaultTableCreator;
import org.example.wowantstobeamillionare.game.database.DefaultDataBaseConnectionPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.wowantstobeamillionare.game.addon.DatabaseTableChecker.tableExists;
import static org.example.wowantstobeamillionare.game.addon.Log4j.logger;

public class QuestionBuffer {

    File readFile = new File("questions.txt");
    public QuestionBuffer() {
    }

    public ArrayList<Question> loadQuestions() throws SQLException {
      try {
          if (tableExists("questions")) {

          } else {

              DefaultTableCreator.createQuestionTable();
          }
          return questionReader();
      }catch(Exception e) {
          e.printStackTrace();
          try{
              Scanner sc = new Scanner(readFile);
              ArrayList<Question> questions = questionReaderFromFile(sc);
              sc.close();
              return questions;
          }catch (FileNotFoundException fileError) {
              logger.error("Error log message",  fileError);
          }
      }
        return new ArrayList<>();
    }
    public ArrayList<Question> questionReader() throws SQLException {

        ArrayList<Question> questions =  new ArrayList<>();

        if (DefaultDataBaseConnectionPool.create().getConnection() == null) {

            try{
                Scanner reader =new Scanner(readFile);
                questions = questionReaderFromFile(reader);
                reader.close();

            }catch(Exception e){
                logger.error("Error reading message", e);
            }
        } else {
            questions =loadQuestionsFromDatabase();
        }
        return questions;
    }
    public ArrayList<Question> loadQuestionsFromDatabase() throws SQLException {
        if(isTableEmptyEfficient("questions")) {

            try{
                Scanner reader =new Scanner(readFile);
                PopulateDatabaseWithQuestions(questionReaderFromFile(reader));
                reader.close();
            }catch(Exception e){
                logger.error("Error loading from database", e);
            }
        }

        ArrayList<Question> questions = new ArrayList<>();
        String sql = "SELECT id, question, ans1, ans2, ans3, ans4, correct_answer FROM questions";
        try (Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                int questionOrder = rs.getInt("id");
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
                questions.add(new Question(questionText, answers, correctAnswer));
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

            String questionText = lineScanner.hasNext() ? lineScanner.nextLine().trim() : "";
            lineScanner.close();

            String answerLine = reader.nextLine();

            var answers = answerReader(answerLine);

            int correctAnswer = Integer.parseInt(reader.nextLine().trim());
            Question q = new Question(questionText, answers, correctAnswer);
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
    private void PopulateDatabaseWithQuestions(ArrayList<Question> questions) {
        String sql = "INSERT INTO questions (question, ans1, ans2, ans3, ans4, correct_answer) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)
        ){
           conn.setAutoCommit(false);

            for (Question q : questions) {
                statement.setString(1, q.getQuestion());
                statement.setString(2, q.getFirstAnswer());
                statement.setString(3, q.getSecondAnswer());
                statement.setString(4, q.getThirdAnswer());
                statement.setString(5, q.getFourthAnswer());
                statement.setInt(6, q.getCorrectAnswer());
                statement.addBatch();
            }
            int[] results = statement.executeBatch();

            conn.commit();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean isTableEmptyEfficient(String tableName) throws SQLException {
        String sql = "SELECT 1 FROM " + tableName + " LIMIT 1";

        try (Connection conn = DefaultDataBaseConnectionPool.create().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            boolean isEmpty = !rs.next(); // If no rows, table is empty
            return isEmpty;
        }
    }
}

