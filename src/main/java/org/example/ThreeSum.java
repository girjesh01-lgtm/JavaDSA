package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    /*
     * Problem: 3Sum
     *
     * Given an integer array nums, find all unique triplets
     * [nums[i], nums[j], nums[k]] such that:
     *
     * 1. i, j, and k are distinct indices
     * 2. nums[i] + nums[j] + nums[k] == 0
     *
     * The resulting list must not contain duplicate triplets.
     *
     * Example:
     * Input:
     * nums = [-1, 0, 1, 2, -1, -1]
     *
     * Output:
     * [[-1, -1, 2], [-1, 0, 1]]
     *
     * Explanation:
     * The triplets:
     * [-1, -1, 2]
     * [-1, 0, 1]
     * both sum to 0 and are unique.
     *
     * Constraints:
     * 0 <= nums.length <= 3000
     * -100000 <= nums[i] <= 100000
     */
    public static void main(String[] args) {
        //int[] nums = {-1, 0, 1, 2, -1, -1};
        int[] nums = {-2,-2, 0, 0, 2, 2};

        List<List<Integer>> answer = threeSum(nums);

        System.out.println(answer);
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int middle = 0; middle < nums.length-2; middle++) {
            if (middle > 0 && nums[middle] == nums[middle - 1]) {
                continue;
            }

            int left = middle + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[left] + nums[middle] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(nums[middle], nums[left], nums[right]));

                    left++;
                    right--;

                    left = shrinkLeft(nums, left, right);
                    right = shrinkRight(nums, left, right);
                } else if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }



        return result;
    }

    private static int shrinkRight(int[] nums, int left, int right) {

        while (left < right && nums[right] == nums[right + 1] ) {
            right--;
        }
        return right;
    }

    private static int shrinkLeft(int[] nums, int left, int right) {
        while (left < right && nums[left] == nums[left - 1] ) {
            left++;
        }
        return left;
    }
}
