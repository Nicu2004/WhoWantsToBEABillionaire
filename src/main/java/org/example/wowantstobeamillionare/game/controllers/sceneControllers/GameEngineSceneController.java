package org.example.wowantstobeamillionare.game.controllers.player.playerBehavior;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.wowantstobeamillionare.game.controllers.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.sceneManager.SceneManager;
import org.example.wowantstobeamillionare.game.liflines.helper.Helper;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionGroupper;

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
    public Button fiftyFifty;
    @FXML
    public Button callFriend;
    @FXML
    private TextField questionField;
    @FXML
    public Button ans1;
    @FXML
    public Button ans2;
    @FXML
    public Button ans3;
    @FXML
    public Button ans4;

    private int score = 0;
    private int currentIndex = 0;
    private Question currentQuestion;
    private final DefaultQuestionRepository defaultQuestionRepository;
    QuestionGroupper questionEngine;
    private List questions;
    private String input;
    QuestionHandler questionHandler;
    private List<Question> grouppedQuestions;
    private Player player;

    public PlayerGameScene() {

        defaultQuestionRepository = new DefaultQuestionRepository();
        questions = new ArrayList<>();
        grouppedQuestions = new ArrayList<>();
        questionHandler = new QuestionHandler();
        questionEngine = new QuestionGroupper(questionHandler);

    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void initialize() throws SQLException {

        questions = defaultQuestionRepository.loadQuestions();
        grouppedQuestions = questionEngine.groupQuestions(questions);
        showCurrentQuestion();
    }
    private void showCurrentQuestion() {
        if(score == grouppedQuestions.size()) {

            SceneManager.switchTo("winnerStage.fxml",player);
            return;
        }
        if(currentIndex == -1) {
            SceneManager.switchTo("lostStage.fxml", player);
            return;
        }
        if(currentIndex<grouppedQuestions.size()) {
            currentQuestion = grouppedQuestions.get(currentIndex);
            questionField.setText(currentQuestion.getQuestion());
            enableAllButtons();
            ans1.setText(currentQuestion.getFirstAnswer());
            ans2.setText(currentQuestion.getSecondAnswer());
            ans3.setText(currentQuestion.getThirdAnswer());
            ans4.setText(currentQuestion.getFourthAnswer());
        }
    }
    public void answerHandler(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        int selectedOption =0 ;
        if(Objects.equals(source.getId(), "ans2"))
            selectedOption = 1;
        if(Objects.equals(source.getId(), "ans3"))
            selectedOption = 2;
        if (Objects.equals(source.getId(), "ans4"))
            selectedOption = 3;
        if(QuestionHandler.answerSwitch(currentQuestion, selectedOption))
        {
            status.setText("Correct");
            status.setFill(Color.GREEN);
            status.setFont(Font.font("System", FontWeight.BOLD, 20));
            score++;
            player.setScore(score);
        }
        else
        {
            currentIndex = -1;
        }
        nextQuestion();
    }
    @FXML
    public void nextQuestion() {
        if(currentIndex != -1) {
            currentIndex++;
        }
        showCurrentQuestion();
    }

    private void enableAllButtons(){
        ans1.setDisable(false);
        ans2.setDisable(false);
        ans3.setDisable(false);
        ans4.setDisable(false);
    }
    public void lifeLineHandler(ActionEvent actionEvent) {

        Button source = (Button) actionEvent.getSource();
        Helper.useLifeLIne(source.getId(), currentQuestion, this);
        AnimationFaderButton.fadeButtonAnnimation(source);

    }
    public void disableBtn1() {
        ans1.setDisable(true);
    }

    public void disableBtn2() {
        ans2.setDisable(true);
    }

    public void disableBtn3() {
        ans3.setDisable(true);
    }

    public void disableBtn4() {
        ans4.setDisable(true);
    }
}
