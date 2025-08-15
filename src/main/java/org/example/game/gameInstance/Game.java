package org.example.game.gameInstance;
import org.example.database.databaseConnection;
import org.example.database.dbIMplemenations.pgDatabaseConnect;
import org.example.game.implementations.ConsoleGameDisplay;
import org.example.game.implementations.StandardGameMode;
import org.example.game.implementations.ContinousGameEndCondition;
import org.example.game.interfaces.GameDisplay;
import org.example.game.interfaces.GameEndCondition;
import org.example.game.interfaces.GameMode;
import org.example.player.implementations.DefaultPlayerService;
import org.example.player.interfaces.PlayerResultRepository;
import org.example.questions.implementations.DefaultQuestionRepository;
import org.example.player.implementations.FilePlayerResultRepository;
import org.example.player.interfaces.PlayerService;
import org.example.player.player.Player;
import org.example.questions.interfaces.QuestionRepository;
import org.example.questions.questionBehaivior.Question;
import org.example.questions.questionBehaivior.QuestionHandler;
import java.sql.SQLException;

import java.util.List;

public class Game {

    private final pgDatabaseConnect pg = new pgDatabaseConnect();
    private final databaseConnection connection = new databaseConnection(pg);

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
                GameEndCondition endCondition) throws SQLException {

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
        connection.initiateDataBaseConnection(pg);

    }
    public Game(boolean isPlayer) throws SQLException {
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
    public void endGame() throws SQLException {

        String playerResults = playerResultRepository.loadPlayerResults();
        gameDisplay.displayPlayerResults(playerResults);
        gameDisplay.displayGameOver();
        if(isPlayer) {
            playerResultRepository.savePlayerResults(pg, player.getName(), player.getScore(),player.getScore()==5?"won":"lost");
        }
    }
}
