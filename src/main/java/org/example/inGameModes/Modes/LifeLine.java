package org.example.inGameModes.Modes;

import org.example.objects.questionBehaivior.Question;
import org.example.utils.Printer;

public interface LifeLine {
    void execute(Question question);
}
class LifelineHistory
{
    private Question lastProcessedQuestion;

    public void recordQuestion(Question question) {
        this.lastProcessedQuestion = question;
    }

    public Question getLastProcessedQuestion() {
        return lastProcessedQuestion;
    }

    public void clear() {
        this.lastProcessedQuestion = null;
    }
}
abstract class QuestionModifyingLifeline implements LifeLine {
    protected final LifelineHistory history;

    protected QuestionModifyingLifeline(LifelineHistory history) {
        this.history = history;
    }

    @Override
    public final void execute(Question question) {
        Question modifiedQuestion = modifyQuestion(question);
        history.recordQuestion(modifiedQuestion);
        Printer.showQuestion(modifiedQuestion);
    }
    protected abstract Question modifyQuestion(Question question);
    protected abstract void displayResult(Question question);
}

