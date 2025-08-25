package org.example.wowantstobeamillionare.controllers.guest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.controllers.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionBuffer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GuestGameScene {

    public Text status;
    @FXML
    private TextField questionField;
    @FXML
    private Button ans1;
    @FXML
    private Button ans2;
    @FXML
    private Button ans3;
    @FXML
    private Button ans4;
    private int currentIndex = 0;
    private Question currentQuestion;
    private DefaultQuestionRepository defaultQuestionRepository;
    private List<Question> questions;
    QuestionHandler questionHandler;
    public GuestGameScene() {

        defaultQuestionRepository = new DefaultQuestionRepository();
        questions = new ArrayList<>();
        questionHandler = new QuestionHandler();
    }
    public void initialize() throws SQLException {
        questions = defaultQuestionRepository.loadQuestions();
        Collections.shuffle(questions);
        showCurrentQuestion();
    }
    private void showCurrentQuestion() {

        if(currentIndex==-1||currentIndex==questions.size())
        {
            questionField.setText("GameOver");
            ans1.setDisable(true);
            ans2.setDisable(true);
            ans3.setDisable(true);
            ans4.setDisable(true);
            return;
        }
           currentQuestion = questions.get(currentIndex);

           questionField.setText(currentQuestion.getQuestion());
           ans1.setText(currentQuestion.getFirstAnswer());
           ans2.setText(currentQuestion.getSecondAnswer());
           ans3.setText(currentQuestion.getThirdAnswer());
           ans4.setText(currentQuestion.getFourthAnswer());
    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button source = (Button) event.getSource();
        int selectedIndex =0;
        if(Objects.equals(source.getId(), "ans2"))
            selectedIndex = 1;
        if(Objects.equals(source.getId(), "ans3"))
            selectedIndex = 2;
        if (Objects.equals(source.getId(), "ans4"))
            selectedIndex = 3;

        if(questionHandler.isCorrectAnswer(currentQuestion, selectedIndex))
        {
            status.setText("Correct");
            status.setStyle("-fx-text-fill: green;");
        }
        else
        {
            status.setText("Incorrect");
            status.setStyle("-fx-text-fill: red;");
            currentIndex = -1;
        }
        nextQuestion();
    }
    @FXML
    private void nextQuestion() {
        if(currentIndex!=-1) {
            currentIndex++;
        }
        showCurrentQuestion();
    }
}
