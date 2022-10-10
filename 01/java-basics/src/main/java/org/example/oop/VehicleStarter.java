package org.example.oop;

public class VehicleStarter {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.start();
        Ford ford = new Ford(10);
        ford.start();
        Tesla tesla = new Tesla(10);
        tesla.start();
    }
}
