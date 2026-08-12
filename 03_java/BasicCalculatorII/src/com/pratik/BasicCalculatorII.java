package com.pratik;

import java.util.Scanner;

public class BasicCalculatorII {

    public static int calculate(String s) {

        int result = 0;
        int previousNumber = 0;
        int currentNumber = 0;
        char operation = '+';

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Build the current number
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            // Process when operator is found or at the end
            if ((!Character.isDigit(ch) && ch != ' ')
                    || i == s.length() - 1) {

                switch (operation) {

                    case '+':
                        result += previousNumber;
                        previousNumber = currentNumber;
                        break;

                    case '-':
                        result += previousNumber;
                        previousNumber = -currentNumber;
                        break;

                    case '*':
                        previousNumber *= currentNumber;
                        break;

                    case '/':
                        previousNumber /= currentNumber;
                        break;

                    default:
                        break;
                }

                operation = ch;
                currentNumber = 0;
            }
        }

        result += previousNumber;

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter expression: ");

        String expression = scanner.nextLine();

        int result = calculate(expression);

        System.out.println("Result: " + result);

        scanner.close();
    }
}