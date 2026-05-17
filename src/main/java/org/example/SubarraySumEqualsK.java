package org.example;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    /*
     * Problem: Subarray Sum Equals K
     *
     * Given an integer array nums and an integer k, return the total number of
     * continuous subarrays whose sum equals k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Example 1:
     * Input: nums = [1, 1, 1], k = 2
     * Output: 2
     * Explanation:
     * The subarrays [1, 1] at indexes 0..1 and 1..2 both have sum 2.
     *
     * Example 2:
     * Input: nums = [1, 2, 3], k = 3
     * Output: 2
     * Explanation:
     * The subarrays [1, 2] and [3] both have sum 3.
     *
     * Constraints:
     * 1 <= nums.length <= 20000
     * -1000 <= nums[i] <= 1000
     * -10000000 <= k <= 10000000
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;

        int answer = subarraySumOptimized(nums, k);

        System.out.println("Number of subarrays with sum " + k + " = " + answer);
    }

    private static int subarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix[i] = (i == 0 ? 0 : prefix[i - 1]) + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (prefix[i] == k) {
                result++;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (prefix[i] - prefix[j] == k) {
                    result++;
                }
            }
        }

        return result;
    }

    private static int subarraySumOptimized(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;

        for (int item : nums) {
            sum = sum + item;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
