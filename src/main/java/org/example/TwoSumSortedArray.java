package org.example;

public class TwoSumSortedArray {

    /*
     * Problem: Two Sum In A Sorted Array
     *
     * Given a sorted array of integers nums, determine whether there exists a pair
     * of numbers whose sum is equal to a given target.
     *
     * Return true if such a pair exists, otherwise return false.
     *
     * Example 1:
     * Input:
     * nums = [1, 3, 4, 6, 8, 10, 13]
     * target = 13
     *
     * Output:
     * true
     *
     * Explanation:
     * 3 + 10 = 13
     *
     * Example 2:
     * Input:
     * nums = [1, 2, 4, 9]
     * target = 8
     *
     * Output:
     * false
     *
     * Explanation:
     * There is no pair whose sum is 8.
     *
     * Constraints:
     * 0 <= nums.length <= 100000
     * nums is sorted in non-decreasing order
     * nums may contain negative numbers and duplicates
     */
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 6, 8, 10, 13};
        int target = 13;

        boolean answer = hasPairWithTargetSum(nums, target);

        System.out.println("Pair exists = " + answer);
    }

    private static boolean hasPairWithTargetSum(int[] nums, int target) {
        boolean result = false;
        if (nums == null || nums.length < 2) {
            return false;
        }

        int left = 0;
        int right = nums.length-1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return true;
            }
            else if (sum < target) {
                left++;
            }else {
                right--;
            }
        }

        return result;
    }
}
