import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        boolean player = true;

        Game gamePlayer = new Game(player);//
        gamePlayer.startGame();

//         Game gameUser = new Game(!player);
//         gameUser.startGame();
    }
}