package org.example.interfaces;

import org.example.objects.player.PlayerResults;

import java.util.HashMap;

public interface PlayerResultRepository {
    String loadPlayerResults();
    void savePlayerResults(HashMap<String, PlayerResults> playerResults);
}
