package org.example.objects.questionBehaivior;

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
        String sql =  "SELECT questionorder, questiontext, ans1, ans2, ans3, correctanswer FROM questions";
        try(Statement st = conn.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql)){
            while (rs.next()) {
                int id = rs.getInt("questionorder");
                String questionText = rs.getString("questiontext");
                String firstAnswer =  rs.getString("ans1");
                String secondAnswer = rs.getString("ans2");
                String thirdAnswer =  rs.getString("ans3");
                int correctAnswer =   rs.getInt("correctanswer");
                List<String> answers = new ArrayList<>();
                answers.add(firstAnswer);
                answers.add(secondAnswer);
                answers.add(thirdAnswer);
                questions.add(new Question(id, questionText, answers, correctAnswer));
            }
        }


        return questions;
    }

}
