package com.pratik;



//Encapsulated class
class Employee {

 // Private data members (data hiding)
 private int id;
 private String name;
 private double salary;

 // Getter methods
 public int getId() {
     return id;
 }

 public String getName() {
     return name;
 }

 public double getSalary() {
     return salary;
 }

 // Setter methods with validation
 public void setId(int id) {
     if (id > 0) {
         this.id = id;
     } else {
         System.out.println("Invalid ID");
     }
 }

 public void setName(String name) {
     this.name = name;
 }

 public void setSalary(double salary) {
     if (salary >= 0) {
         this.salary = salary;
     } else {
         System.out.println("Invalid Salary");
     }
 }
}

public class EncapsulationDemo {

 public static void main(String[] args) {

     Employee emp = new Employee();

     // Setting values using setters
     emp.setId(101);
     emp.setName("Tarkesh");
     emp.setSalary(55000);

     // Getting values using getters
     System.out.println("Employee ID: " + emp.getId());
     System.out.println("Employee Name: " + emp.getName());
     System.out.println("Employee Salary: " + emp.getSalary());
 }
}
