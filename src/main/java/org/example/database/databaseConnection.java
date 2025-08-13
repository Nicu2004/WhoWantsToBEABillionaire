package org.example.database;

import org.example.dbIMplemenations.pgDatabaseConnect;
import org.example.implementations.DefaultQuestionRepository;
import org.example.objects.questionBehaivior.Question;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Calendar;
import java.util.List;
import org.example.interfaces.*;




public class databaseConnection {

    private static pgDatabaseConnect pg;

    public databaseConnection(pgDatabaseConnect pg)
    {
        databaseConnection.pg =pg;
    }

    public void initiateDataBaseConnection(pgDatabaseConnect pg) throws SQLException {

        pg.connect();
//        Statement st = pg.getConnection().createStatement();
//        String sql = "INSERT INTO questions(question_order, " +
//                "question, " +
//                "ans1, " +
//                "ans2, " +
//                "ans3, " +
//                "ans4, " +
//                "corect_answer) VALUES(?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement pst = pg.getConnection().prepareStatement(sql);
//        List<Question> questions;
//        DefaultQuestionRepository questionRepository = new DefaultQuestionRepository();
//        questions = questionRepository.loadQuestions();
//        for(var q: questions)
//        {
//            pst.setInt(1, q.getQuestionId());
//            pst.setString(2, q.getQuestion());
//            pst.setString(3, q.getFirstAnswer());
//            pst.setString(4,q.getSecondAnswer());
//            pst.setString(5, q.getThirdAnswer());
//            pst.setString(6,q.getFourthAnswer());
//            pst.setInt(7, q.getCorrectAnswer());
//            pst.executeUpdate();
//        }
//
//
//        Calendar cal = Calendar.getInstance();
//        System.out.println(cal.getTime());
    }

    public static pgDatabaseConnect getConn() {
        return pg;
    }

}