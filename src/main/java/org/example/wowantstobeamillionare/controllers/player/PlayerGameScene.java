package org.example.wowantstobeamillionare.controllers.player;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.controllers.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.controllers.sceneManager.SceneManager;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PlayerGameScene {
    @FXML
    public Text status;
    @FXML
    public Button audience;
    @FXML
    public Button fifty;
    @FXML
    public Button cFriend;
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
    private int score = 0;
    private int currentIndex = 0;
    private Question currentQuestion;
    private final DefaultQuestionRepository defaultQuestionRepository;
    QuestionEngine questionEngine;
    private List questions;
    private String input;
    QuestionHandler questionHandler;
    private List<Question> grouppedQuestions;
    public PlayerGameScene() {

        defaultQuestionRepository = new DefaultQuestionRepository();
        questions = new ArrayList<>();
        grouppedQuestions = new ArrayList<>();
        questionHandler = new QuestionHandler();
        questionEngine = new QuestionEngine(questionHandler);
    }
    public void initialize() throws SQLException {
        questions = defaultQuestionRepository.loadQuestions();
        grouppedQuestions = questionEngine.groupQuestions(questions);
        showCurrentQuestion();
    }
    private void showCurrentQuestion() {
        if(score == grouppedQuestions.size()) {
            SceneManager.switchTo("winnerStage.fxml");
            return;
        }
        if(currentIndex == -1) {
            SceneManager.switchTo("lostStage.fxml");
            return;
        }
        currentQuestion = grouppedQuestions.get(currentIndex);
        System.out.println(currentQuestion.getQuestion());
        questionField.setText(currentQuestion.getQuestion());
        ans1.setText(currentQuestion.getFirstAnswer());
        ans2.setText(currentQuestion.getSecondAnswer());
        ans3.setText(currentQuestion.getThirdAnswer());
        ans4.setText(currentQuestion.getFourthAnswer());
    }
    public void answerHandler(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        String selectedOption ="0";
        if(Objects.equals(source.getId(), "ans2"))
            selectedOption = "1";
        if(Objects.equals(source.getId(), "ans3"))
            selectedOption = "2";
        if (Objects.equals(source.getId(), "ans4"))
            selectedOption = "3";
        if(questionHandler.answerSwitch(currentQuestion, selectedOption))
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
    public void nextQuestion() {
        if(currentIndex != -1) {
            currentIndex++;
            score++;

        }
        showCurrentQuestion();
    }
}
