import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "questions.txt";
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
                System.out.println("File will be created at: " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name =  input.next();

        Player player = new Player(name);
        System.out.print(player.getName());
        int[] percentages  = PercentageDistribution.generatePercentages();
        System.out.print(percentages);
        GameClass Game = new GameClass(fileName, player);
        Game.StartGame();

    }
}