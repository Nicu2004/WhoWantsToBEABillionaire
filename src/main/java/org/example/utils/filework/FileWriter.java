package org.example.utils.filework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    private final String name;
    private final int score;
    public  FileWriter(String name, int score)
    {
        this.name = name;
        this.score = score;
    }
    public void appendToFile(){

       try
       {
           String line = name + "," + score+ (score==5?",Won":",Lost ") + System.lineSeparator();
           Files.write(Paths.get("scores.txt"), line.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
       }catch (IOException e)
           {
                System.err.println("Failed to write scores.txt "+e.getMessage());
           }
    }
}
