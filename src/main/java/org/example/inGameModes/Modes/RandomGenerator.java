package org.example.inGameModes.Modes;

import java.util.Random;

public interface RandomGenerator {
    int nextInt(int bound);
    boolean nextBoolean();
}
class DefaultRandomGenerator implements RandomGenerator {
    private final Random random  = new Random();
    @Override
    public int nextInt(int bound) {
        return random.nextInt(bound);
    }
    @Override
    public boolean nextBoolean() {
        return random.nextBoolean();
    }
}
