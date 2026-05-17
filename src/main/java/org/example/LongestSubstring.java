package org.example;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    public static void main(String[] args) {
        String input = "abcabcbb";
        //String input = "a";
        int answer = lengthOfLongestSubstringOptimized(input);

        System.out.println("Longest substring length for \"" + input + "\" = " + answer);
    }

    private static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            freq.put(current, freq.getOrDefault(current, 0)+1);
            while (freq.get(current) > 1) {
                freq.put(s.charAt(left), freq.get(s.charAt(left))-1);
                left++;
            }
            maxLength = Math.max(right - left + 1, maxLength);
        }

        return maxLength;
    }

    private static int lengthOfLongestSubstringOptimized(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();

        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (lastSeen.containsKey(current) && lastSeen.get(current) >= left) {
                left = lastSeen.get(current) + 1;
            }
            lastSeen.put(current, right);
            maxLength = Math.max(right - left + 1, maxLength);
        }

        return maxLength;
    }
}
