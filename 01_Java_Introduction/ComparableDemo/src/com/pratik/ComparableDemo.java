package com.pratik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Custom class
class Student implements Comparable<Student> {

    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Natural ordering by marks (ascending)
    @Override
    public int compareTo(Student s) {
        return Double.compare(this.marks, s.marks);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + marks;
    }
}

public class ComparableDemo {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student(101, "Rahul", 85.5));
        students.add(new Student(102, "Amit", 72.0));
        students.add(new Student(103, "Sneha", 91.0));

        System.out.println("Before Sorting:");
        for (Student s : students) {
            System.out.println(s);
        }

        Collections.sort(students); // Uses compareTo()

        System.out.println("\nAfter Sorting by Marks:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
