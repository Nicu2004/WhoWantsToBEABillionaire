package org.example.wowantstobeamillionare.game.player.player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayerResultLoader {
    
    public static String loadPlayerResults(String filename)  {
        HashMap<String, PlayerResults> map = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split(",");
                if(parts.length >=3) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    String result = parts[2].trim();
                    map.put(name, new PlayerResults(score, result));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.toString(map.keySet().toArray());
    }

}
