package com.pratik;

public class FirstNonRepeatingCharacter {

    public static int firstUniqChar(String s) {

        int[] count = new int[26];

        // Count frequency
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Find first unique character
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        String s = "loveleetcode";

        int result = firstUniqChar(s);

        System.out.println("First non-repeating index: " + result);
    }
}