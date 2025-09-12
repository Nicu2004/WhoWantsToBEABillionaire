package org.example.wowantstobeamillionare.game.questions.questionEngine;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface QuestionService {
    void showCurrentQuestion(int currentIndex,TextField questionField, Button ans1, Button ans2, Button ans3, Button ans4);
}
