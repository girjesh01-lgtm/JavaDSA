package org.example;

import java.util.Arrays;

public class TriangleNumbers {

    /*
     * Problem: Triangle Numbers
     *
     * Given an integer array nums, count the number of triplets that can form
     * the sides of a valid triangle.
     *
     * A triplet (a, b, c) can form a triangle only if all of the following hold:
     *
     * a + b > c
     * a + c > b
     * b + c > a
     *
     * In other words, the sum of every pair of sides must be greater than the
     * third side.
     *
     * Example 1:
     * Input:
     * nums = [2, 2, 3, 4]
     *
     * Output:
     * 3
     *
     * Explanation:
     * Valid triplets are:
     * [2, 3, 4] using the first 2
     * [2, 3, 4] using the second 2
     * [2, 2, 3]
     *
     * Example 2:
     * Input:
     * nums = [4, 2, 3, 4]
     *
     * Output:
     * 4
     *
     * Explanation:
     * Valid triplets are:
     * [2, 3, 4] using the first 4
     * [2, 3, 4] using the second 4
     * [2, 4, 4]
     * [3, 4, 4]
     *
     * Constraints:
     * 0 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     */
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4};

        int answer = triangleNumber(nums);

        System.out.println("Triangle count = " + answer);
    }

    private static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;

        for (int a = 0; a < nums.length - 2; a++) {
            int left = a+1;
            int right = nums.length-1;
            while(left < right) {
                if (nums[a] + nums[left] > nums[right]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }

        return count;
    }
}
