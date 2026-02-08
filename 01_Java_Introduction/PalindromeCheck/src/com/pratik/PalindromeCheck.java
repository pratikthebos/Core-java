package com.pratik;



public class PalindromeCheck {

    public static void main(String[] args) {

        String str = "madam";
        String reversed = "";

        // Reverse string
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }

        // Check palindrome
        if (str.equals(reversed)) {
            System.out.println(str + " is Palindrome");
        } else {
            System.out.println(str + " is Not Palindrome");
        }
    }
}
