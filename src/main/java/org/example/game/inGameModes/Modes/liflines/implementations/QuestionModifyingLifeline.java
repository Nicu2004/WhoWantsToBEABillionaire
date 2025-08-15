package org.example.game.inGameModes.Modes.liflines.implementations;

import org.example.game.inGameModes.Modes.liflines.interfaces.LifeLine;
import org.example.questions.questionBehaivior.Question;
import org.example.utils.Printer;

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
