package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    /*
    public static void main(String[] args) {
        String input = "122";
        char[] arr = input.toCharArray();
        Arrays.sort(arr);
        String sortedStr = new String(arr);
        System.out.println("sortedStr    ------> "+sortedStr);

        solve(0, sortedStr, new StringBuilder());
    }

    private static void solve(int start, String sortedStr, StringBuilder stringBuilder) {

        System.out.println(stringBuilder);

        for (int i=start; i<sortedStr.length(); i++) {
            if(i > start && sortedStr.charAt(i) == sortedStr.charAt(i-1)) {
                continue;
            }

            stringBuilder.append(sortedStr.charAt(i));

            solve(i+1, sortedStr, stringBuilder);

            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

     */

    public static void main(String[] args) {
        String input = "122";
        char[] arr = input.toCharArray();
        Arrays.sort(arr);

        List<List<Character>> result = new ArrayList<>();
        backtrack(0, arr, new ArrayList<>(), result);

        System.out.println(result);
    }

    private static void backtrack(int start, char[] arr, List<Character> current, List<List<Character>> result) {

        result.add(new ArrayList<>(current));

        for (int i = start; i < arr.length; i++) {

            if (i > start && arr[i] == arr[i - 1]) continue;

            current.add(arr[i]);

            backtrack(i + 1, arr, current, result);

            current.remove(current.size() - 1);
        }
    }

}
