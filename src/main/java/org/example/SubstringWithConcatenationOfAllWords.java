package org.example;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {

    /*
     * Problem 30: Substring With Concatenation Of All Words
     *
     * You are given a string s and an array of strings words.
     * All strings in words are of the same length.
     *
     * A concatenated string is a string that exactly contains all the strings of
     * any permutation of words concatenated.
     *
     * Example:
     * If words = ["ab", "cd", "ef"], then these are valid concatenated strings:
     * "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", "efcdab"
     *
     * "acdbef" is not valid because it is not the concatenation of any permutation
     * of words.
     *
     * Return an array/list of the starting indices of all concatenated substrings
     * in s.
     *
     * The answer can be returned in any order.
     *
     * Example 1:
     * Input:
     * s = "barfoothefoobarman"
     * words = ["foo", "bar"]
     *
     * Output:
     * [0, 9]
     *
     * Explanation:
     * The substring starting at 0 is "barfoo".
     * It is the concatenation of ["bar", "foo"], which is a permutation of words.
     *
     * The substring starting at 9 is "foobar".
     * It is the concatenation of ["foo", "bar"], which is a permutation of words.
     *
     * Example 2:
     * Input:
     * s = "wordgoodgoodgoodbestword"
     * words = ["word", "good", "best", "word"]
     *
     * Output:
     * []
     *
     * Explanation:
     * There is no concatenated substring.
     *
     * Example 3:
     * Input:
     * s = "barfoofoobarthefoobarman"
     * words = ["bar", "foo", "the"]
     *
     * Output:
     * [6, 9, 12]
     *
     * Explanation:
     * The substring starting at 6 is "foobarthe".
     * It is the concatenation of ["foo", "bar", "the"].
     *
     * The substring starting at 9 is "barthefoo".
     * It is the concatenation of ["bar", "the", "foo"].
     *
     * The substring starting at 12 is "thefoobar".
     * It is the concatenation of ["the", "foo", "bar"].
     *
     * Constraints:
     * 1 <= s.length <= 10000
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 30
     * s and words[i] consist of lowercase English letters.
     */
    public static void main(String[] args) {
        //String s = "barfoothefoobarman";
        //String[] words = {"foo", "bar"};

        //String s = "aaaaaaaaaaaaaa";
        //String[] words = {"aa", "aa"};

        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};


        List<Integer> answer = findSubstringOptimized(s, words);

        System.out.println(answer);
    }

    private static List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        Map<String, Integer> freqMapping = new HashMap<>();
        for (String word : words) {
            freqMapping.put(word, freqMapping.getOrDefault(word, 0) + 1);
        }
        int wordLength = words[0].length();
        int substringLength = words[0].length()* words.length;


        for (int i = 0; i <= s.length()-substringLength; i++) {
            Map<String, Integer> freq = new HashMap<>(freqMapping);
            int start = i;
            int end = i+wordLength;
            String current = s.substring(start, end);
            while (freq.size() > 0 && freq.containsKey(current)) {
                if (freq.get(current) == 1) {
                    freq.remove(current);
                }
                else {
                    freq.put(current, freq.get(current)-1);
                }
                start = end;
                end = start+wordLength;
                current = s.substring(start, end);
            }

            if(freq.size() == 0) {
                result.add(i);
            }
        }

        return result;
    }

    private static List<Integer> findSubstringOptimized(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty() ||
                words == null || words.length == 0) {
            return result;
        }

        // Frequency mapping of required words
        Map<String, Integer> targetMap = new HashMap<>();
        for (String word : words) {
            targetMap.put(word, targetMap.getOrDefault(word, 0) + 1);
        }

        int wordLength = words[0].length();
        int wordCount = words.length;



        // try every possible alignment
        for (int offset = 0; offset < wordLength; offset++) {

            int left = offset;
            int matchedWords = 0;

            Map<String, Integer> windowMap = new HashMap<>();

            for (int right = offset; right + wordLength <= s.length(); right += wordLength) {
                String currentWord = s.substring(right, right + wordLength);

                // check if current word is valid
                if (targetMap.containsKey(currentWord)) {
                    windowMap.put(currentWord, windowMap.getOrDefault(currentWord, 0) + 1);
                    matchedWords++;

                    // shrink window if frequency exceeds
                    while (windowMap.get(currentWord) > targetMap.get(currentWord)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        if (windowMap.get(leftWord) == 0) {
                            windowMap.remove(leftWord);
                        }
                        left += wordLength;
                        matchedWords--;
                    }

                    if (matchedWords == wordCount) {
                        result.add(left);
                        String leftWord = s.substring(left, left + wordLength);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        if (windowMap.get(leftWord) == 0) {
                            windowMap.remove(leftWord);
                        }
                        left += wordLength;
                        matchedWords--;
                    }
                } else {
                    // invalid word --> reset the window
                    windowMap.clear();
                    matchedWords = 0;
                    left = right + wordLength;
                }
            }
        }
        Collections.sort(result);
        return result;
    }
}
