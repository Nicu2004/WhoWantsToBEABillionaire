package org.example.wowantstobeamillionare.game.game.inGameModes.Modes.liflines.implementations;

import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

public class LifelineHistory
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

