package com.pratik;



class Student {

    int id;
    String name;
    int age;

    // 1️⃣ Default Constructor
    Student() {
        System.out.println("Default Constructor Called");
    }

    // 2️⃣ Parameterized Constructor
    Student(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Parameterized Constructor Called");
    }

    // 3️⃣ Constructor Overloading + Chaining
    Student(int id, String name, int age) {
        this(id, name); // calls parameterized constructor
        this.age = age;
        System.out.println("Overloaded Constructor Called");
    }

    void display() {
        System.out.println(id + " " + name + " " + age);
    }
}

public class Cons {

    public static void main(String[] args) {

        Student s1 = new Student(); // Default
        Student s2 = new Student(101, "Rahul"); // Parameterized
        Student s3 = new Student(102, "Sneha", 23); // Overloaded

        s2.display();
        s3.display();
    }
}
