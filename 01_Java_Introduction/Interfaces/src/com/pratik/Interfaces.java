package com.pratik;



//Interface 1
interface Payment {
 void pay(double amount);   // abstract method
}

//Interface 2
interface Refund {
 void refund(double amount);
}

//Implementing multiple interfaces
class UpiPayment implements Payment, Refund {

 @Override
 public void pay(double amount) {
     System.out.println("Paid ₹" + amount + " using UPI");
 }

 @Override
 public void refund(double amount) {
     System.out.println("Refunded ₹" + amount + " to UPI account");
 }
}

//Interface with default method (Java 8+)
interface Notification {
 default void notifyUser() {
     System.out.println("Notification sent to user");
 }
}

//Implementing interface with default method
class PaymentService implements Notification {
 // No need to override default method
}

public class Interfaces {

 public static void main(String[] args) {

     Payment payment = new UpiPayment();   // Interface reference
     payment.pay(1500);

     Refund refund = new UpiPayment();
     refund.refund(500);

     PaymentService service = new PaymentService();
     service.notifyUser();
 }
}
