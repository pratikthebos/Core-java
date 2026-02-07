package com.pratik;



public class BasicJavaDemo {

    public static void main(String[] args) {

        // 1️⃣ Variable Declaration
        int id = 101;
        String name = "Tarkesh";
        double salary = 55000.50;

        // 2️⃣ Printing Output
        System.out.println("Employee Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);

        // 3️⃣ If Condition
        if (salary > 50000) {
            System.out.println("High Salary");
        } else {
            System.out.println("Normal Salary");
        }

        // 4️⃣ Loop Example
        System.out.println("Numbers from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
    }
}
