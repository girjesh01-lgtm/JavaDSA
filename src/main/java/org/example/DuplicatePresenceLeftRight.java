package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatePresenceLeftRight {

    /*
     * Problem: Duplicate Presence On Left And Right
     *
     * Given an integer array nums, return two binary strings:
     *
     * 1. leftPresence:
     *    For each index i, put '1' if nums[i] is present anywhere to its left,
     *    otherwise put '0'.
     *
     * 2. rightPresence:
     *    For each index i, put '1' if nums[i] is present anywhere to its right,
     *    otherwise put '0'.
     *
     * Example:
     * Input: nums = [1, 2, 3, 1, 2, 4]
     *
     * leftPresence:
     * index 0 -> 1 has not appeared before -> 0
     * index 1 -> 2 has not appeared before -> 0
     * index 2 -> 3 has not appeared before -> 0
     * index 3 -> 1 appeared on left      -> 1
     * index 4 -> 2 appeared on left      -> 1
     * index 5 -> 4 has not appeared before -> 0
     *
     * rightPresence:
     * index 0 -> 1 appears on right -> 1
     * index 1 -> 2 appears on right -> 1
     * index 2 -> 3 does not appear on right -> 0
     * index 3 -> 1 does not appear on right -> 0
     * index 4 -> 2 does not appear on right -> 0
     * index 5 -> 4 does not appear on right -> 0
     *
     * Output: ["000110", "110000"]
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 4};

        List<String> answer = duplicatePresence(nums);

        System.out.println(answer);
    }

    private static List<String> duplicatePresence(int[] nums) {
        StringBuilder leftPresence = new StringBuilder();
        StringBuilder rightPresence = new StringBuilder();

        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            leftPresence.append(seen.contains(num) ? '1' : '0');
            seen.add(num);
        }

        seen.clear();

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            rightPresence.append(seen.contains(num) ? '1' : '0');
            seen.add(num);
        }

        rightPresence.reverse();

        return List.of(leftPresence.toString(), rightPresence.toString());
    }
}
