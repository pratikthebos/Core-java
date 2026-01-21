package com.pratik;



//Encapsulated class
class Student {

 // Private data members (data hiding)
 private int id;
 private String name;
 private double marks;

 // Public getters and setters
 public int getId() {
     return id;
 }

 public void setId(int id) {
     if (id > 0) {        // validation
         this.id = id;
     }
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public double getMarks() {
     return marks;
 }

 public void setMarks(double marks) {
     if (marks >= 0 && marks <= 100) {   // validation
         this.marks = marks;
     }
 }
}

public class Encapsulation {

 public static void main(String[] args) {

     Student student = new Student();

     // Setting values using setters
     student.setId(101);
     student.setName("Tarkesh");
     student.setMarks(85.5);

     // Getting values using getters
     System.out.println("ID: " + student.getId());
     System.out.println("Name: " + student.getName());
     System.out.println("Marks: " + student.getMarks());
 }
}
