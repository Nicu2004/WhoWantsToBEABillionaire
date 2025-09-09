package org.example.wowantstobeamillionare.game.controllers.questionEngine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.example.wowantstobeamillionare.game.controllers.SceneManager;
import org.example.wowantstobeamillionare.game.controllers.gameEngine.ButtonsBehavior;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionGroupper;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.QuestionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultQuestionService implements QuestionService {

    private List<Question> questions;
    private Question currentQuestion;
    private List<Question> groupedQuestions;
    private DefaultQuestionRepository defaultQuestionRepository;
    private QuestionHandler questionHandler;
    private QuestionGroupper questionGrouper;
    private Player player;
    public DefaultQuestionService(Player  player) throws SQLException {

        this.player = player;
        defaultQuestionRepository = new DefaultQuestionRepository();
        questions = new ArrayList<>();
        questionHandler = new QuestionHandler();
        questionGrouper = new QuestionGroupper(questionHandler);
        questions = defaultQuestionRepository.loadQuestions();
        groupedQuestions = questionGrouper.groupQuestions(questions);
    }
    QuestionGroupper grouper;
    @Override
    public void showCurrentQuestion(int currentIndex, TextField questionField, Button ans1, Button ans2, Button ans3, Button ans4) {
        if(player.getScore()==groupedQuestions.size()){
            {
                SceneManager.switchTo("winnerStage.fxml",player);
            }
        }
        if(currentIndex==-1)
        {
            Platform.runLater(() -> {
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    SceneManager.switchTo("lostStage.fxml", player);
                }));
                timeline.play();
            });
            return;
        }
        if(currentIndex<groupedQuestions.size()) {
            currentQuestion = groupedQuestions.get(currentIndex);
            questionField.setText(currentQuestion.getQuestion());
            ButtonsBehavior.enableAllButtons(ans1, ans2, ans3, ans4);
            ans1.setText(currentQuestion.getFirstAnswer());
            ans2.setText(currentQuestion.getSecondAnswer());
            ans3.setText(currentQuestion.getThirdAnswer());
            ans4.setText(currentQuestion.getFourthAnswer());
        }
    }
    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}
