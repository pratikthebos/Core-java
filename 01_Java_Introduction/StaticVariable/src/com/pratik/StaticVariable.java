package com.pratik;

class Student {
    int id;              // instance variable
    String name;         // instance variable
    static String college = "CDAC";  // static variable

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void display() {
        System.out.println(id + " " + name + " " + college);
    }
}

public class StaticVariable {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Amit");
        Student s2 = new Student(102, "Rohit");

        s1.display();
        s2.display();
    }
}
