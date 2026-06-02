package org.example;

import java.util.ArrayList;
import java.util.List;

public class FourSum {

    /*
     * Problem: 4Sum
     *
     * Given an integer array nums and an integer target, return all unique
     * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     *
     * 1. a, b, c, and d are distinct indices
     * 2. nums[a] + nums[b] + nums[c] + nums[d] == target
     *
     * The solution set must not contain duplicate quadruplets.
     *
     * Example 1:
     * Input:
     * nums = [1, 0, -1, 0, -2, 2]
     * target = 0
     *
     * Output:
     * [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
     *
     * Example 2:
     * Input:
     * nums = [2, 2, 2, 2, 2]
     * target = 8
     *
     * Output:
     * [[2, 2, 2, 2]]
     *
     * Constraints:
     * 0 <= nums.length <= 200
     * -1000000000 <= nums[i] <= 1000000000
     * -1000000000 <= target <= 1000000000
     */
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> answer = fourSum(nums, target);

        System.out.println(answer);
    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        return new ArrayList<>();
    }
}
