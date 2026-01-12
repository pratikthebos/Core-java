package com.pratik;

public class Function {

    // 1. No parameter, no return
    void greet() {
        System.out.println("Hello, Java!");
    }

    // 2. Method with parameters
    void add(int a, int b) {
        System.out.println("Addition = " + (a + b));
    }

    // 3. Method with return value
    int multiply(int a, int b) {
        return a * b;
    }

    // 4. Static method
    static void sayHello() {
        System.out.println("Hello from static method");
    }

    // Main method
    public static void main(String[] args) {

        // Creating object
        Function obj = new Function();

        // Calling methods
        obj.greet();
        obj.add(10, 20);

        int result = obj.multiply(5, 4);
        System.out.println("Multiplication = " + result);

        // Calling static method
        sayHello();
    }
}
