package org.example.wowantstobeamillionare.game.game.gameInstance;
import org.example.wowantstobeamillionare.game.database.PgStatemantClass;
import org.example.wowantstobeamillionare.game.game.implementations.ConsoleGameDisplay;
import org.example.wowantstobeamillionare.game.game.implementations.StandardGameMode;
import org.example.wowantstobeamillionare.game.game.implementations.ContinousGameEndCondition;
import org.example.wowantstobeamillionare.game.game.interfaces.GameDisplay;
import org.example.wowantstobeamillionare.game.game.interfaces.GameEndCondition;
import org.example.wowantstobeamillionare.game.game.interfaces.GameMode;
import org.example.wowantstobeamillionare.game.player.implementations.DefaultPlayerService;
import org.example.wowantstobeamillionare.game.player.interfaces.PlayerResultRepository;
import org.example.wowantstobeamillionare.game.questions.implementations.DefaultQuestionRepository;
import org.example.wowantstobeamillionare.game.player.implementations.FilePlayerResultRepository;
import org.example.wowantstobeamillionare.game.player.interfaces.PlayerService;
import org.example.wowantstobeamillionare.game.player.player.Player;
import org.example.wowantstobeamillionare.game.questions.interfaces.QuestionRepository;
import org.example.wowantstobeamillionare.game.questions.questionBehaivior.Question;
import org.example.wowantstobeamillionare.controllers.questionBehaivior.QuestionHandler;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.List;

public class Game {


    private static final Statement statement;

    static {
        try {
            statement = PgStatemantClass.createStmt();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final QuestionRepository questionRepository;
    private final PlayerResultRepository playerResultRepository;
    private final GameMode gameMode;
    private final GameDisplay gameDisplay;
    private final GameEndCondition endCondition;
    private final boolean isPlayer;
    private Player player;
    private String input;

    public Game(boolean isPlayer,
                QuestionRepository questionRepository,
                PlayerService playerService,
                PlayerResultRepository playerResultRepository,
                GameMode gameMode,
                GameDisplay gameDisplay,
                GameEndCondition endCondition) throws SQLException {

        this.isPlayer = isPlayer;
        this.questionRepository = questionRepository;
        this.playerResultRepository = playerResultRepository;
        this.gameMode = gameMode;
        this.gameDisplay = gameDisplay;
        this.endCondition = endCondition;

        // Create player if needed
        if (isPlayer) {
            this.player = new Player("player");
        }

    }
    public Game(boolean isPlayer) throws SQLException {
        this(isPlayer,
                new DefaultQuestionRepository(),
                new DefaultPlayerService(),
                new FilePlayerResultRepository("scores.txt"),
                new StandardGameMode(new GameTypes(new QuestionHandler())), // Inject GameTypes
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
    public void endGame() throws SQLException {

        String playerResults = playerResultRepository.loadPlayerResults();
        gameDisplay.displayPlayerResults(playerResults);
        gameDisplay.displayGameOver();
        if(isPlayer) {
            playerResultRepository.savePlayerResults(statement, player.getName(), player.getScore(),player.getScore()==5?"won":"lost");
        }
    }
}
