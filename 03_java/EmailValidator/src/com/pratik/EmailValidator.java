package com.pratik;

import java.util.Scanner;

public class EmailValidator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (email.contains("@") && email.contains(".")
                && !email.startsWith("@")
                && !email.endsWith(".")) {
            System.out.println("Valid Email");
        } else {
            System.out.println("Invalid Email");
        }

        sc.close();
    }
}