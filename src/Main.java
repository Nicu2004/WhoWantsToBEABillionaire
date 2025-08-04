import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();
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
    }
}