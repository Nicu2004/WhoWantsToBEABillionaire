package org.example.wowantstobeamillionare.game.questions.questionBehaivior;

import org.example.wowantstobeamillionare.game.database.PgStatemantClass;
import org.example.wowantstobeamillionare.game.player.implementations.DefaultPlayerService;
import org.example.wowantstobeamillionare.game.utils.filework.LoadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionBuffer {

    public QuestionBuffer() {}

    public ArrayList<Question> loadQuestions() throws SQLException {

        final String fileName = LoadFile.loadFile();
        assert fileName != null;
        File questionsFile = new File(fileName);
        Scanner reader;
        try {
            reader = new Scanner(questionsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return questionReader();
    }

    public  ArrayList<Question> questionReader() throws SQLException {
        ArrayList<Question> questions = new ArrayList<>();
       Statement statement = PgStatemantClass.createStmt();

        String sql =  "SELECT question_order, question, ans1, ans2, ans3, ans4, corect_answer FROM questions";
        try(
        ResultSet rs = statement.executeQuery(sql)){
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
}
