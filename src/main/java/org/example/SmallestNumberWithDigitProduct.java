package org.example;

import java.util.Arrays;

public class SmallestNumberWithDigitProduct {

    /*
     * Problem: Smallest Number Whose Product Of Digits Is N
     *
     * Given a positive integer n, find the smallest positive integer such that
     * the product of its digits is equal to n.
     *
     * If no such number exists, return -1.
     *
     * Example 1:
     * Input:
     * n = 36
     *
     * Output:
     * 49
     *
     * Explanation:
     * 4 * 9 = 36
     * Other numbers like 66 also have digit product 36, but 49 is smaller.
     *
     * Example 2:
     * Input:
     * n = 13
     *
     * Output:
     * -1
     *
     * Explanation:
     * 13 is prime and cannot be expressed as a product of digits from 2 to 9.
     *
     * Example 3:
     * Input:
     * n = 7
     *
     * Output:
     * 7
     *
     * Explanation:
     * The single digit 7 itself has digit product 7.
     *
     * Notes:
     * - If n is between 0 and 9, think carefully about what the answer should be.
     * - The result should be the numerically smallest valid number.
     */
    public static void main(String[] args) {
        int n = 36;

        int answer = smallestNumber(n);

        System.out.println("Smallest number = " + answer);
    }

    private static int smallestNumber(int n) {
        if (n >= 0 && n <= 9) {
            return n;
        }

        int result = 0;
        int[] numberArray = new int[10];

        for (int i = 9; i > 1; i--) {
            while (n % i == 0) {
                numberArray[i] += 1;
                n = n / i;
            }
        }
        if (n != 1) {
            return -1;
        } else {
            for (int j = 2; j <= 9; j++) {
                while (numberArray[j] > 0) {
                    result = result * 10 + j;
                    numberArray[j]--;
                }
            }
        }

        return result;
    }
}
