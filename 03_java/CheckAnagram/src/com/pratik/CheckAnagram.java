package com.pratik;

public class CheckAnagram {

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) return false;

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }

        for (int num : count) {
            if (num != 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        String s = "listen";
        String t = "silent";

        boolean result = isAnagram(s, t);

        System.out.println("Are Anagrams: " + result);
    }
}