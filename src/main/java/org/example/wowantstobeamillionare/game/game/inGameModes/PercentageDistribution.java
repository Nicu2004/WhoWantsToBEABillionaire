package org.example.wowantstobeamillionare.game.game.inGameModes;

import java.util.Random;

public class PercentageDistribution {

    public static int[] generatePercentages() {
        Random rand = new Random();
        int a = rand.nextInt(101);
        int b = rand.nextInt(101-a);
        int c = rand.nextInt(101-a-b);
        int d = 100-a-b-c;
        return new int[]{a,b,c,d};
    }
}
