
package com.pratik;

import java.util.Scanner;

public class RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            int index = current - 'a';

            if (visited[index]) {
                continue;
            }

            while (stack.length() > 0
                    && stack.charAt(stack.length() - 1) > current
                    && lastIndex[stack.charAt(stack.length() - 1) - 'a'] > i) {

                char removed = stack.charAt(stack.length() - 1);
                stack.deleteCharAt(stack.length() - 1);
                visited[removed - 'a'] = false;
            }

            stack.append(current);
            visited[index] = true;
        }

        return stack.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a lowercase string: ");
        String s = sc.nextLine();

        String result = removeDuplicateLetters(s);

        System.out.println("Lexicographically smallest result: " + result);

        sc.close();
    }
}