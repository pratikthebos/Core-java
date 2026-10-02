
package com.pratik;

import java.util.Scanner;

public class BulbSwitcher {

    public static int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of bulbs: ");
        int n = sc.nextInt();

        int result = bulbSwitch(n);

        System.out.println("Number of bulbs that remain ON: " + result);

        sc.close();
    }
}