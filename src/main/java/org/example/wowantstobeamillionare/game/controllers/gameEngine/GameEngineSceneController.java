package org.example.wowantstobeamillionare.game.controllers.gameEngine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.wowantstobeamillionare.game.addon.AnimationFaderButton;
import org.example.wowantstobeamillionare.game.controllers.questionEngine.AnswerValidator;
import org.example.wowantstobeamillionare.game.controllers.questionEngine.DefaultQuestionService;
import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;
import org.example.wowantstobeamillionare.game.liflines.helper.Helper;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameEngineSceneController {
    @FXML
    public Text status;
    @FXML
    public Button audience;
    @FXML
    public Button fiftyFifty;
    @FXML
    public Button callFriend;
    public Text money;
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

    private int currentIndex = 0;
    private final DefaultQuestionRepository defaultQuestionRepository;
    private List<Question> questions;
    private Player player;
    private final AnswerValidator answerValidator;
    private DefaultQuestionService defaultQuestionService;

    public GameEngineSceneController() {

        defaultQuestionRepository = new DefaultQuestionRepository();
        questions = new ArrayList<>();
        answerValidator = new AnswerValidator();
    }

    public void initialize() throws SQLException {

        questions = defaultQuestionRepository.loadQuestions();
    }
    public void answerHandler(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        currentIndex = answerValidator.answerHandlerService(source, defaultQuestionService.getCurrentQuestion(), status, money,player, currentIndex);
        ButtonsBehavior.disableAllButtons(ans1, ans2, ans3, ans4);
        nextQuestion();
    }
    @FXML
    public void nextQuestion() {
        Platform.runLater(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                status.setText("");
                defaultQuestionService.showCurrentQuestion(currentIndex, questionField, ans1, ans2, ans3, ans4);
            }));
            timeline.play();
        });
    }
    public void disableSpecificButton(int buttonNumber)
    {
        switch(buttonNumber)
            {
            case 1:ans1.setDisable(true);break;
            case 2:ans2.setDisable(true);break;
            case 3:ans3.setDisable(true);break;
            case 4:ans4.setDisable(true);break;
            }
    }
    public void lifeLineHandler(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Helper.useLifeLIne(source.getId(),defaultQuestionService.getCurrentQuestion(), this);
        AnimationFaderButton.fadeButtonAnnimation(source);
    }
    public void setPlayer(Player player) throws SQLException {
        this.player = player;
        defaultQuestionService = new DefaultQuestionService(player);
        defaultQuestionService.showCurrentQuestion(currentIndex, questionField, ans1, ans2, ans3, ans4);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    //docker files, docker compose
}
