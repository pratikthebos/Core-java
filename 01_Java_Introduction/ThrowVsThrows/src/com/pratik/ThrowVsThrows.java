package com.pratik;



//Custom Exception
class InvalidAgeException extends Exception {
 public InvalidAgeException(String message) {
     super(message);
 }
}

public class ThrowVsThrows {

 // Method using 'throws'
 static void validateAge(int age) throws InvalidAgeException {

     if (age < 18) {
         // 'throw' is used to explicitly throw an exception
         throw new InvalidAgeException("Age must be 18 or above");
     }

     System.out.println("Valid age: " + age);
 }

 public static void main(String[] args) {

     try {
         validateAge(16);   // Will throw exception
     } catch (InvalidAgeException e) {
         System.out.println("Exception caught: " + e.getMessage());
     }

     System.out.println("Program continues...");
 }
}
