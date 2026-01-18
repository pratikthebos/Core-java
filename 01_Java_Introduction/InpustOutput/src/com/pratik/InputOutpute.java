package com.pratik;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class InputOutpute {

    public static void main(String[] args) throws IOException {

        // 1️⃣ Using Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.println("Scanner Output -> Name: " + name + ", Age: " + age);

        // 2️⃣ Using BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter city: ");
        String city = reader.readLine();

        System.out.print("Enter pincode: ");
        int pincode = Integer.parseInt(reader.readLine());

        System.out.println("BufferedReader Output -> City: " + city + ", Pincode: " + pincode);

        scanner.close();
    }
}