package com.pratik;

import java.util.Random;

public class RandomPasswordGenerator {

    public static void main(String[] args) {

        int length = 10; // password length
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        System.out.println("Generated Password: " + password);
    }
}