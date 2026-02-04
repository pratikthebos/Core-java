package com.pratik;



import java.util.*;

// Custom class
class Student {

    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + marks;
    }
}

public class ComparatorDemo {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student(101, "Rahul", 85.5));
        students.add(new Student(102, "Amit", 72.0));
        students.add(new Student(103, "Sneha", 91.0));

        System.out.println("Before Sorting:");
        for (Student s : students) {
            System.out.println(s);
        }

        // 1️⃣ Sort by marks (ascending)
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return Double.compare(a.marks, b.marks);
            }
        });

        System.out.println("\nSorted by Marks:");
        for (Student s : students) {
            System.out.println(s);
        }

        // 2️⃣ Sort by name (alphabetical)
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.name.compareTo(b.name);
            }
        });

        System.out.println("\nSorted by Name:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
