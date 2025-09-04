package org.example.wowantstobeamillionare.game.controllers.sceneControllers.gameEngine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.wowantstobeamillionare.game.addon.AnimationFaderButton;
import org.example.wowantstobeamillionare.game.controllers.sceneControllers.SceneManager;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionHandler;
import org.example.wowantstobeamillionare.game.liflines.helper.Helper;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionGroupper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GameEngineSceneController {
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

    public GameEngineSceneController() {

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

            Platform.runLater(() -> {
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    SceneManager.switchTo("lostStage.fxml", player);
                }));
                timeline.play();
            });
            return;
        }
        if(currentIndex<grouppedQuestions.size()) {
            currentQuestion = grouppedQuestions.get(currentIndex);
            questionField.setText(currentQuestion.getQuestion());
            ButtonsBehavior.enableAllButtons(ans1, ans2, ans3, ans4);
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
            status.setText("Incorrect");
            status.setFill(Color.RED);
            status.setFont(Font.font("System", FontWeight.BOLD, 20));
            currentIndex = -1;
        }
        ButtonsBehavior.disableAllButtons(ans1, ans2, ans3, ans4);
        nextQuestion();
    }
    @FXML
    public void nextQuestion() {
        Platform.runLater(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                status.setText("");
                currentIndex++;
                showCurrentQuestion();
            }));
            timeline.play();
        });
        showCurrentQuestion();
    }

    public void lifeLineHandler(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Helper.useLifeLIne(source.getId(), currentQuestion, this);
        AnimationFaderButton.fadeButtonAnnimation(source);
    }
}
