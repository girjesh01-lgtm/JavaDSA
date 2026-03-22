package org.example;

import java.util.Arrays;

public class Permutations {

    public static void main(String[] args) {
        //String input = "aB3";
        //String input = "dddd4";
        String input = "122";
        String sortedStr = sortInSpecialFashion(input);
        System.out.println("sortedStr    ------> "+sortedStr);
        solve(sortedStr, new boolean[input.length()], new StringBuilder());
    }

    public static String sortInSpecialFashion(String str) {
        StringBuilder digits = new StringBuilder();
        StringBuilder upper = new StringBuilder();
        StringBuilder lower = new StringBuilder();

        for(char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
            else if (Character.isUpperCase(c)) {
                upper.append(c);
            }
            else if (Character.isLowerCase(c)) {
                lower.append(c);
            }
        }

        char[] digitArr = digits.toString().toCharArray();
        Arrays.sort(digitArr);

        char[] upperArr = upper.toString().toCharArray();
        Arrays.sort(upperArr);

        char[] lowerArr = lower.toString().toCharArray();
        Arrays.sort(lowerArr);

        return new String(digitArr) + new String(upperArr) + new String(lowerArr);
    }


    public static void solve(String str,boolean[] used, StringBuilder sb) {

        if(str.length() == sb.length()) {
            System.out.println(sb.toString());
            return;
        }
        System.out.println(sb.toString());

        for (int i=0; i<str.length(); i++) {
            if(i > 0 && str.charAt(i) == str.charAt(i-1) && !used[i-1]) {
                continue;
            }

            if(!used[i]) {

                sb.append(str.charAt(i));
                used[i] = true;

                solve(str, used, sb);

                sb.deleteCharAt(sb.length()-1);
                used[i] = false;
            }
        }

    }

}