package com.pratik;

final class Vehicle {   // final class cannot be inherited

    final int MAX_SPEED = 120; // final variable (constant)

    final void displaySpeed() { // final method cannot be overridden
        System.out.println("Maximum Speed: " + MAX_SPEED);
    }
}

class Bike {
    void show() {
        System.out.println("Bike class method");
    }
}

public class FinalKeyword {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.displaySpeed();

        

        Bike bike = new Bike();
        bike.show();
    }
}