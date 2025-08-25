package org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.implementations;
import org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.implementations.QuestionModifyingLifeline;
import org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.implementations.LifelineHistory;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.ConsoleQuestionDisplay;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionDisplay;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionReducer;

public class FiftyFifty  extends QuestionModifyingLifeline {

    private final QuestionReducer questionReducer;
    private final QuestionDisplay display;

    public FiftyFifty(QuestionReducer questionReducer, LifelineHistory history, QuestionDisplay display) {
        super(history);
        this.questionReducer = questionReducer;
        this.display = display;
    }
    public FiftyFifty()
    {
        this(new QuestionReducer(new DefaultRandomGenerator()),
                new LifelineHistory(),
                new ConsoleQuestionDisplay()
        );
    }
    @Override
    public Question modifyQuestion(Question question) {
        return questionReducer.reduceTwoAnswers(question);
    }
    @Override
    protected void displayResult(Question question) {
        display.displayRemainingAnswers(question.getAnswers());
    }
    public  Question getLastReducedQuestion() {
        return history.getLastProcessedQuestion();
    }
}

