package org.example.interfaces;
import org.example.objects.questionBehaivior.Question;
import java.util.List;

public interface QuestionRepository {
    List<Question> loadQuestions();
}
