package org.example;

public class RegexMatching {

    static Boolean[][] memo;
    public static void main(String[] args) {
        String s = "aaa";
        String p = "a*ab";
        System.out.println(isMatch(s,p));
    }

    public static boolean isMatch(String s, String p) {
        memo = new Boolean[s.length()+1][p.length()+1];
        return dfs(0,0,s,p);
    }

    public static boolean dfs(int i, int j, String s, String p) {

        if (memo[i][j] != null) {
           return memo[i][j];
        }

        if (j == p.length()) {
           return memo[i][j] = (i == s.length());
        }

        boolean firstMatch = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean ans;

        if (j+1 < p.length() && p.charAt(j+1) == '*') {
            ans = dfs(i,j+2, s, p) ||       // skip a*
                    (firstMatch && dfs(i+1, j, s, p));
        }
        else {
            ans = firstMatch && dfs(i+1, j+1, s, p);
        }

        return memo[i][j] = ans;

    }



}
