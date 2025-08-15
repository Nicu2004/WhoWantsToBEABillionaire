package org.example.game.inGameModes.Modes.liflines.implementations;

import org.example.game.inGameModes.Modes.liflines.interfaces.RandomGenerator;

import java.util.Random;

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

