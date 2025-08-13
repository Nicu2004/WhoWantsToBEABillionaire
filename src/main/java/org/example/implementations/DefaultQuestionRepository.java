package org.example.implementations;

import org.example.interfaces.QuestionRepository;
import org.example.objects.questionBehaivior.Question;
import org.example.objects.questionBehaivior.QuestionBuffer;

import java.sql.SQLException;
import java.util.List;

public class DefaultQuestionRepository implements QuestionRepository {

    @Override
    public List<Question> loadQuestions() throws SQLException {
        QuestionBuffer buffer = new QuestionBuffer();
        return buffer.loadQuestions();
    }
}
