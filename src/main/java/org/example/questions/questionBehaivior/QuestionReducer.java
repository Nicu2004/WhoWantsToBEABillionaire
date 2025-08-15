package org.example.questions.questionBehaivior;

import org.example.game.inGameModes.Modes.liflines.interfaces.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class QuestionReducer{

    private final RandomGenerator randomGenerator;
    public QuestionReducer(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
    public Question reduceTwoAnswers(Question question) {
        if(question.getAnswers().size() < 2) {
            throw new IllegalArgumentException("Question needs at least 2 answers");
        }
        int correctAnswer = question.getCorrectAnswer();
        int randomAnswer = selectRandomIncorrect(question, correctAnswer);

        List<String> reducedAnswers = createReducedAnswerList(question, correctAnswer,  randomAnswer);

        int newCorrectIndex = determineCorrectIndex();

        return new Question(
                question.getQuestionId(),
                question.getQuestion(),
                reducedAnswers,
                newCorrectIndex
        );

    }
    private int selectRandomIncorrect(Question question, int correctAnswer) {
        int randomAnswer;
        do{
            randomAnswer = randomGenerator.nextInt(question.getAnswers().size());
        }while(randomAnswer == correctAnswer);
        return randomAnswer;
    }
    private List<String> createReducedAnswerList(Question question, int correctAnswer, int randomAnswer) {
        List<String> reducedAnswers = new ArrayList<>();
        boolean firstIsCorrect = randomGenerator.nextBoolean();

        if(firstIsCorrect) {
            reducedAnswers.add(question.getAnswers().get(correctAnswer));
            reducedAnswers.add(question.getAnswers().get(randomAnswer));
        }
        else{
            reducedAnswers.add(question.getAnswers().get(randomAnswer));
            reducedAnswers.add(question.getAnswers().get(correctAnswer));
        }
        return reducedAnswers;
    }
    private int determineCorrectIndex() {
        return randomGenerator.nextBoolean() ? 0 : 1;
    }
}
