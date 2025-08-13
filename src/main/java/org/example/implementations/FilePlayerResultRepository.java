package org.example.implementations;

import org.example.interfaces.PlayerResultRepository;
import org.example.objects.player.PlayerResultLoader;
import org.example.objects.player.PlayerResults;

import java.util.HashMap;

public class FilePlayerResultRepository implements PlayerResultRepository {
    private final String fileName;

    public FilePlayerResultRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String loadPlayerResults() {
        // Use your existing PlayerResultLoader
        return PlayerResultLoader.loadPlayerResults(fileName);
    }

    @Override
    public void savePlayerResults(HashMap<String, PlayerResults> results) {
        // Implementation for saving results
        // You can implement this later
    }
}
