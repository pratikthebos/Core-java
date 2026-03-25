package com.pratik;

import java.util.*;

public class FindAllAnagrams {

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) return result;

        int[] count = new int[26];

        // Count frequency of p
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }

        int left = 0, right = 0, k = p.length();

        while (right < s.length()) {

            // Include current char
            count[s.charAt(right) - 'a']--;

            if (count[s.charAt(right) - 'a'] >= 0) {
                k--;
            }

            // Window size reached
            if (right - left + 1 == p.length()) {

                if (k == 0) {
                    result.add(left);
                }

                // Remove left char
                count[s.charAt(left) - 'a']++;
                if (count[s.charAt(left) - 'a'] > 0) {
                    k++;
                }

                left++;
            }

            right++;
        }

        return result;
    }

    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> result = findAnagrams(s, p);

        System.out.println("Anagram indices: " + result);
    }
}