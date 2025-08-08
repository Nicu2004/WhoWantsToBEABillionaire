import java.io.FileNotFoundException;

public class Game {

        private final boolean isPlayer;
        private Player player;

        public Game(boolean isPlayer) {//this constructor is called if there is a player, not a user

            this.isPlayer = isPlayer;
            if(isPlayer) {
                this.player = new Player(Player.getPlayerName());
            }
        }
        public void startGame() throws FileNotFoundException {

            Printer.displayWelcomeMessage(isPlayer, player);
            var questions  = Question.loadQuestions();
            if (isPlayer) {
                GameTypes.playerGame(player, questions);
            } else {
                GameTypes.userGame(questions);
            }
        }
}
