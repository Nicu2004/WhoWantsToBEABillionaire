import java.util.Scanner;

public class Player {

    String name;

    public Player(String playerName) {
        this.name = playerName;
    }

    public static String getPlayerName() {
        {
            System.out.print("Please enter your name: ");
            String name = GameTypes.input.nextLine().trim();

            return name;
        }
    }
}
