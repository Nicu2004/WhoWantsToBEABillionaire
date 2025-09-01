package org.example.wowantstobeamillionare.game.players.guest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;

import java.awt.*;
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
    private final DefaultQuestionRepository defaultQuestionRepository;
    private List<Question> questions;

    ActionEvent actionEvent;
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
    private void showCurrentQuestion() throws SQLException {

        if(currentIndex==-1||currentIndex==questions.size())
        {
            questionField.setText("GameOver");
            ans1.setDisable(true);
            ans2.setDisable(true);
            ans3.setDisable(true);
            ans4.setDisable(true);
            Platform.runLater(() -> {
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                    Stage stage =  (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.close();
                }));
                timeline.play();
            });
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
    private void handleButtonAction(ActionEvent event) throws SQLException {

        actionEvent = event;
        Button source = (Button) event.getSource();
        int selectedIndex =0;
        if(Objects.equals(source.getId(), "ans2"))
            selectedIndex = 1;
        if(Objects.equals(source.getId(), "ans3"))
            selectedIndex = 2;
        if (Objects.equals(source.getId(), "ans4"))
            selectedIndex = 3;

        if(QuestionHandler.answerSwitch(currentQuestion, selectedIndex))
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
    private void nextQuestion() throws SQLException {
        if(currentIndex!=-1) {
            currentIndex++;
        }
        showCurrentQuestion();
    }
}
