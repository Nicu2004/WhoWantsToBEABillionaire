package org.example.wowantstobeamillionare.game.questions.interfaces;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    List<Question> loadQuestions() throws SQLException;
}
