package org.example.interfaces;
import org.example.objects.questionBehaivior.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    List<Question> loadQuestions() throws SQLException;
}
