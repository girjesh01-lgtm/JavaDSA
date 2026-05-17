package org.example;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    /*
     * Problem: Longest Repeating Character Replacement
     *
     * You are given a string s and an integer k.
     *
     * You can choose any character of the string and change it to any other
     * uppercase English character. You can perform this operation at most k times.
     *
     * Return the length of the longest substring containing the same letter you can
     * get after performing at most k replacements.
     *
     * Example 1:
     * Input: s = "ABAB", k = 2
     * Output: 4
     * Explanation:
     * Replace the two 'A's with two 'B's, or replace the two 'B's with two 'A's.
     * The whole string can become "BBBB" or "AAAA", so the answer is 4.
     *
     * Example 2:
     * Input: s = "AABABBA", k = 1
     * Output: 4
     * Explanation:
     * Replace one 'A' in the substring "ABBA" to get "BBBB".
     * The longest possible substring length is 4.
     *
     * Constraints:
     * 1 <= s.length <= 100000
     * s consists of only uppercase English letters.
     * 0 <= k <= s.length
     */
    public static void main(String[] args) {
        String s = "ABAB";
        int k = 1;

        int answer = characterReplacement(s, k);

        System.out.println("Longest repeating character replacement length = " + answer);
    }

    private static int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();

        int longest = 0;
        int mostFreq = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            freq.put(current, freq.getOrDefault(current, 0) + 1);

            mostFreq = Math.max(mostFreq, freq.get(current));

            while (right - left + 1 - mostFreq > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                left++;
            }

            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }
}
