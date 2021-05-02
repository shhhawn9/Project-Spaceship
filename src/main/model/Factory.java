package model;

import ui.GameFrame;

import java.util.Random;

public class Factory {
    private static final Factory instance = new Factory();

    private Factory() {}

    public static Factory getInstance() {
        return instance;
    }

    // EFFECTS: Generate and return a new asteroid.
    public Asteroid makeAsteroid() {
        return new Asteroid(GameFrame.WIDTH,
                random(0, GameFrame.HEIGHT - 40),
                random(10, 40),
                random(10, 40),
                random(1, 10));
    }

    // EFFECTS: Generate and return a integer between the given range.
    private int random(int min, int max) {
        Random rand = new Random();
        return min + (int) (rand.nextDouble() * (max - min));
    }
}
