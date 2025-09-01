package org.example.wowantstobeamillionare.game.utils;

import org.example.wowantstobeamillionare.game.players.player.playerBehavior.Player;

public class Printer {

    private Player player;
    public Printer(Player player) {
        this.player = player;
    }

    public static String displayWelcomeMessage(boolean isPlayer, Player player) {
       if(isPlayer)
       {
           return "Welcome "+ player.getName()+" to Who Wants to be a Millionaire";
       }
       return "Welcome to game Trial";
    }
}
