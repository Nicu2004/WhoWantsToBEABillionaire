package org.example.wowantstobeamillionare.game.questions.questionBehaivior;

import org.example.wowantstobeamillionare.game.database.PgStatemantClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionBuffer {

    public QuestionBuffer() {}

    public ArrayList<Question> loadQuestions() throws SQLException {

        return questionReader();
    }

    public  ArrayList<Question> questionReader() throws SQLException {
        ArrayList<Question> questions = new ArrayList<>();
        Statement statement = PgStatemantClass.createStmt();

        String sql =  "SELECT question_order, question, ans1, ans2, ans3, ans4, correct_answer FROM questions";
        try(
        ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()) {
                int id = rs.getInt("question_order");
                String questionText = rs.getString("question");
                String firstAnswer =  rs.getString("ans1");
                String secondAnswer = rs.getString("ans2");
                String thirdAnswer =  rs.getString("ans3");
                String fourthAnswer =  rs.getString("ans4");
                int correctAnswer =   rs.getInt("correct_answer");
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
}
