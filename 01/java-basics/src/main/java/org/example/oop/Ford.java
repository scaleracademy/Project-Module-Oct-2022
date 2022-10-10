package org.example.oop;

public class Ford extends Vehicle {
    int fuel;

    public Ford(int fuel) {
        this.fuel = fuel;
    }

    @Override
    void start() {
        if (fuel <= 0) {
            throw new IllegalStateException("Not enough fuel");
        }
        super.start();
    }
}
