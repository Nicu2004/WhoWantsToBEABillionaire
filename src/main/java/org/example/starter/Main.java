package org.example.starter;

import org.example.gameInstance.Game;


public class Main {
    public static void main(String[] args) {

        boolean player = true;

         Game gameUser = new Game(player);
         gameUser.startGame();
    }
}