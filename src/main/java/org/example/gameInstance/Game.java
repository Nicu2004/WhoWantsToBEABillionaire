package org.example.gameInstance;
import org.example.implementations.ConsoleGameDisplay;
import org.example.implementations.StandardGameMode;
import org.example.implementations.ContinousGameEndCondition;
import org.example.implementations.DefaultPlayerService;
import org.example.implementations.DefaultQuestionRepository;
import org.example.implementations.FilePlayerResultRepository;
import org.example.interfaces.*;
import org.example.objects.player.Player;
import org.example.objects.player.PlayerResults;
import org.example.objects.questionBehaivior.Question;
import org.example.objects.questionBehaivior.QuestionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Game {

    private final QuestionRepository questionRepository;
    private final PlayerResultRepository playerResultRepository;
    private final GameMode gameMode;
    private final GameDisplay gameDisplay;
    private final GameEndCondition endCondition;
    private final boolean isPlayer;
    private Player player;

    public Game(boolean isPlayer,
                QuestionRepository questionRepository,
                PlayerService playerService,
                PlayerResultRepository playerResultRepository,
                GameMode gameMode,
                GameDisplay gameDisplay,
                GameEndCondition endCondition) {

        this.isPlayer = isPlayer;
        this.questionRepository = questionRepository;
        this.playerResultRepository = playerResultRepository;
        this.gameMode = gameMode;
        this.gameDisplay = gameDisplay;
        this.endCondition = endCondition;

        // Create player if needed
        if (isPlayer) {
            this.player = playerService.createPlayer(Player.getPlayerName());
        }
    }
    public Game(boolean isPlayer) {
        this(isPlayer,
                new DefaultQuestionRepository(),
                new DefaultPlayerService(),
                new FilePlayerResultRepository("scores.txt"),
                new StandardGameMode(new GameTypes(new QuestionHandler(GameTypes.input))), // Inject GameTypes
                new ConsoleGameDisplay(),
                new ContinousGameEndCondition());
    }


    public void startGame() throws SQLException {

        gameDisplay.displayWelcomeMessage(isPlayer, player);

        List<Question> questions = questionRepository.loadQuestions();
        int gamesPlayed = 0;
        while (true) {
            boolean continueGame = playSingleGame(questions);
            gamesPlayed++;
            if(!endCondition.shouldContinue(continueGame, gamesPlayed)){
                break;
            }

        }
        endGame();
    }
    public boolean playSingleGame(List<Question> questions){
        if(isPlayer) {
            return gameMode.playGame(questions,player);
        }else {
            return gameMode.playGame(questions);
        }
    }
    public void endGame() {

        String playerResults = playerResultRepository.loadPlayerResults();
        gameDisplay.displayPlayerResults(playerResults);
        gameDisplay.displayGameOver();
    }
}
