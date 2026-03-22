package org.example;

import java.util.HashMap;
import java.util.HashSet;

public class MaxSubStringWithAtmostK {
    public static void main(String[] args) {
        System.out.println(calculateLongestSubstring("eceba", 2));
        System.out.println(calculateLongestSubstring("aa", 1));
        System.out.println(calculateLongestSubstring("eceba", 5));
    }

    public static String calculateLongestSubstring(String source, int k) {

        if (k == 0 || source.isEmpty()) {
            return "";
        }

        int left=0, right=0;

        HashMap<Character, Integer> windowFreq = new HashMap<>();

        int maxLeft=0;
        int maxLength=0;

        while (right < source.length()) {
            char currentChar = source.charAt(right);
            windowFreq.put(currentChar, windowFreq.getOrDefault(currentChar, 0)+1);



            // start shrinking till window has k or less unique characters
            while (windowFreq.size() > k) {
                char leftChar = source.charAt(left);

                windowFreq.computeIfPresent(leftChar, (key, currentValue) -> {
                   int newValue = currentValue-1;
                   return newValue == 0 ? null : newValue;
                });

                left++;

            }

            if(right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxLeft = left;
            }

            right++;
        }
        return source.substring(maxLeft, maxLeft+maxLength);
    }
}

