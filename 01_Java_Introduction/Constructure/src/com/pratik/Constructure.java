package com.pratik;



class Student {

    int id;
    String name;
    int age;

    // 1️⃣ Default Constructor
    Student() {
        System.out.println("Default constructor called");
    }

    // 2️⃣ Parameterized Constructor
    Student(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Parameterized constructor called");
    }

    // 3️⃣ Constructor Overloading
    Student(int id, String name, int age) {
        this(id, name);   // Calling another constructor
        this.age = age;
        System.out.println("Overloaded constructor called");
    }

    void display() {
        System.out.println(id + " " + name + " " + age);
    }
}

public class Constructure {
    public static void main(String[] args) {

        Student s1 = new Student();
        Student s2 = new Student(101, "Tarkesh");
        Student s3 = new Student(102, "Tarkesh", 23);

        s2.display();
        s3.display();
    }
}
