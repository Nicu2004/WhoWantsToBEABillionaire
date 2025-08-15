package org.example.questions.implementations;

import org.example.questions.interfaces.QuestionRepository;
import org.example.questions.questionBehaivior.Question;
import org.example.questions.questionBehaivior.QuestionBuffer;

import java.sql.SQLException;
import java.util.List;

public class DefaultQuestionRepository implements QuestionRepository {

    @Override
    public List<Question> loadQuestions() throws SQLException {
        QuestionBuffer buffer = new QuestionBuffer();
        return buffer.loadQuestions();
    }
}
