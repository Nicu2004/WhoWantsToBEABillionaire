package org.example.questions.questionBehaivior;

import java.util.List;

public class ConsoleQuestionDisplay implements QuestionDisplay {

    @Override
    public void displayRemainingAnswers(List<String> answers) {
        System.out.println("Remaining answers: "+answers);
    }
}
