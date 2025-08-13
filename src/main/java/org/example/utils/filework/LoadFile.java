package org.example.utils.filework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadFile {
    public static String loadFile() {

        String fileName = "questions.txt";
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File will be created at: " + path.toAbsolutePath());
                System.out.println("File created: " + fileName);
            }
            return  fileName;
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
        return null;
    }
}
