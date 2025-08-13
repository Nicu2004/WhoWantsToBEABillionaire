package org.example.interfaces;

import org.example.objects.player.PlayerResults;

import java.util.HashMap;

public interface PlayerResultRepository {
    HashMap<String, PlayerResults> loadPlayerResults();
    void savePlayerResults(HashMap<String, PlayerResults> playerResults);
}
