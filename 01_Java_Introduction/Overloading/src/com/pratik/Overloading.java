package com.pratik;

class Overloading {

    // Method with 2 int parameters
    int add(int a, int b) {
        return a + b;
    }

    // Method with 3 int parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method with double parameters
    double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Overloading obj = new Overloading();

        System.out.println(obj.add(10, 20));        // 30
        System.out.println(obj.add(10, 20, 30));    // 60
        System.out.println(obj.add(10.5, 20.5));    // 31.0
    }
}
