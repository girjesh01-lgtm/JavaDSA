package org.example;

public class SoldierUniformStabilization {

    /*
     * Problem: Soldier Uniform Stabilization
     *
     * A binary string represents soldiers standing in a line:
     *
     * '0' means red uniform.
     * '1' means blue uniform.
     *
     * In one second, every occurrence of "01" changes to "10" at the same time.
     * This means if a red soldier is immediately to the left of a blue soldier,
     * their order changes so the blue soldier moves left and the red soldier moves
     * right.
     *
     * Repeat this operation every second until there is no "01" left.
     *
     * Return the number of seconds needed for the string to become stable.
     *
     * Stable means all blue soldiers are on the left and all red soldiers are on
     * the right, so no "01" pattern remains.
     *
     * Example 1:
     * Input: s = "0101010"
     *
     * t = 0: 0101010
     * t = 1: 1010100
     * t = 2: 1101000
     * t = 3: 1110000
     *
     * Output: 3
     *
     * Example 2:
     * Input: s = "00000000001"
     * Output: 10
     *
     * Explanation:
     * The single '1' has to move left across ten '0' characters.
     *
     * Example 3:
     * Input: s = "111000"
     * Output: 0
     *
     * Explanation:
     * There is no "01" pattern, so the string is already stable.
     */
    public static void main(String[] args) {
        String s = "0101010";

        //int simulationAnswer = secondsToStabilizeBySimulation(s);
        int optimizedAnswer = secondsToStabilizeOptimized(s);

        //System.out.println("Simulation answer = " + simulationAnswer);
        System.out.println("Optimized answer = " + optimizedAnswer);
    }

    private static int secondsToStabilizeBySimulation(String s) {
        int seconds = 0;

        while (s.contains("01")) {
            s = s.replace("01", "10");
            seconds++;
        }

        return seconds;
    }

    private static int secondsToStabilizeOptimized(String s) {
        int zeros = 0;
        int seconds = 0;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '0') {
                zeros++;
            } else if (zeros > 0) {
                seconds = Math.max(seconds + 1, zeros);
            }
        }

        return seconds;
    }
}
