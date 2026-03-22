package org.example;

// Shortest Substring Containing All Characters
/*
Problem: Shortest Substring Containing All Characters
Problem Statement

You are given two strings:

s (the source string)

t (the target string)

Your task is to find the smallest substring in s that contains all characters of t, including duplicates.

If no such substring exists, return an empty string.

Input

s: a non-empty string of length up to 10^5

t: a non-empty string of length up to 10^5

Both strings consist of ASCII characters.

Output

Return the minimum length substring of s that contains all characters from t.

If multiple answers exist, return any one.

If no such substring exists, return "".

Examples
Input:
s = "ADOBECODEBANC"
t = "ABC"

Output:
"BANC"

Input:
s = "a"
t = "aa"

Output:
""

Input:
s = "aa"
t = "aa"

Output:
"aa"

 */

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShortestSubstring {

    public static void main(String[] args) {
        String sourceStr = "FDESAAABBBCDEFCSABBC";
        String targetStr = "ABBC";

        System.out.println(findShortestSubstring(sourceStr, targetStr));
        System.out.println(findShortestSubstringUsingSourceAndTarget(sourceStr, targetStr));
    }


    public static String findShortestSubstring(String source, String target) {

        int minLength = Integer.MAX_VALUE;
        String shortSubstring = "";

        Map<Character, Integer> targetFrequency = calculateCharFrequenciesOfTarget(target);
        int required = targetFrequency.size();

        Map<Character, Integer> windowCharFrequencies = new HashMap<>();
        int left = 0;

        for (int right=0; right < source.length(); right++) {

            Character currChar = source.charAt(right);

            if(targetFrequency.containsKey(currChar)) {
               windowCharFrequencies.put(currChar, windowCharFrequencies.getOrDefault(currChar, 0)+1);

               if(Objects.equals(windowCharFrequencies.get(currChar), targetFrequency.get(currChar))) {
                   required--;

                   // this means we have all the chars from target in the window, so this is valid window
                   if(required == 0) {
                       // so now shrink till the window is valid
                       while(required == 0) {

                           Character leftChar = source.charAt(left);

                           if(windowCharFrequencies.containsKey(leftChar)) {
                               shortSubstring = (right-left+1) < minLength ? source.substring(left, right+1) : shortSubstring;
                               minLength =  Math.min(minLength, right-left+1);

                               if ((windowCharFrequencies.get(leftChar)-1 >= targetFrequency.get(leftChar))) {

                                   windowCharFrequencies.put(leftChar, windowCharFrequencies.get(leftChar)-1);
                               }else{
                                   // this means window became invalid on shrinking the window from left
                                   // so this is minimum length valid window
                                   //System.out.println("left = "+left+"   right = "+right+"   minlength = "+minLength);
                                   //System.out.println("shortSubstring = "+shortSubstring);
                                   //System.out.println("valid substring = "+source.substring(left, right+1));
                                   windowCharFrequencies.put(leftChar, windowCharFrequencies.get(leftChar)-1);

                                   required++;
                               }
                           }
                           left++;
                       }
                   }
               }
            }
        }

        return shortSubstring;
    }


    private static Map<Character, Integer> calculateCharFrequenciesOfTarget(String target) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : target.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0)+1);
        }
        return counts;
    }


    public static String findShortestSubstringUsingSourceAndTarget(String source, String target) {
        if(source == null || target == null || source.length() < target.length()) {
            return "";
        }

        // Frequency of characters in target
        Map<Character, Integer> targetFreq = new HashMap<>();
        for(char c : target.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0)+1);
        }

        int required = targetFreq.size();   // number of unique chars still unmet
        int formed = 0;                     // number of unique chars currently satisfied

        Map<Character, Integer> windowFreq = new HashMap<>();

        int left=0;
        int minLength = Integer.MAX_VALUE;
        int minleft=0;

        for (int right=0; right < source.length(); right++) {
            char currChar = source.charAt(right);
            windowFreq.put(currChar, windowFreq.getOrDefault(currChar, 0)+1);

            if(targetFreq.containsKey(currChar)
                    && windowFreq.get(currChar).intValue() == targetFreq.get(currChar).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {

                if(right -left +1 < minLength) {
                    minLength = right - left + 1;
                    minleft = left;
                }

                char leftChar = source.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                if (targetFreq.containsKey(leftChar)
                        && windowFreq.get(leftChar) < targetFreq.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : source.substring(minleft, minleft+minLength);

    }
}
