package org.example.questions.interfaces;
import org.example.questions.questionBehaivior.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    List<Question> loadQuestions() throws SQLException;
}
