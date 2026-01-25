package com.pratik;



public class TryCatch{

    public static void main(String[] args) {

        try {
            // Risky code
            int a = 10;
            int b = 0;
            int result = a / b;   // ArithmeticException

            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            // Specific exception
            System.out.println("Exception caught: " + e.getMessage());

        } catch (Exception e) {
            // Generic exception
            System.out.println("General exception caught");

        } finally {
            // Always executes
            System.out.println("Finally block executed (Resource cleanup)");
        }

        System.out.println("Program continues...");
    }
}
