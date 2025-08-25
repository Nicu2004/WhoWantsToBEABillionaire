package org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.implementations;

import org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.interfaces.LifeLine;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.utils.Printer;

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
