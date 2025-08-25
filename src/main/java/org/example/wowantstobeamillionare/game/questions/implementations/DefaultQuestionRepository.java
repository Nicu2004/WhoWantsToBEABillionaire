package org.example.wowantstobeamillionare.game.questions.implementations;

import org.example.wowantstobeamillionare.game.questions.interfaces.QuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionBuffer;

import java.sql.SQLException;
import java.util.List;

public class DefaultQuestionRepository implements QuestionRepository {

    public DefaultQuestionRepository() {}
    @Override
    public List<Question> loadQuestions() throws SQLException {
        QuestionBuffer buffer = new QuestionBuffer();
        return buffer.loadQuestions();
    }
}
