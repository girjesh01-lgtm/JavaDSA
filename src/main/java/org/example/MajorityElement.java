package org.example;

public class MajorityElement {

    /*
     * Problem: Majority Element
     *
     * Given an integer array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than n / 2 times.
     *
     * You may assume that the majority element always exists in the array.
     *
     * Example 1:
     * Input:
     * nums = [3, 2, 3]
     *
     * Output:
     * 3
     *
     * Example 2:
     * Input:
     * nums = [2, 2, 1, 1, 1, 2, 2]
     *
     * Output:
     * 2
     *
     * Constraints:
     * 1 <= nums.length <= 50000
     * -1000000000 <= nums[i] <= 1000000000
     *
     * Follow-up:
     * Can you solve the problem in O(n) time and O(1) space?
     */
    public static void main(String[] args) {
        //int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int[] nums = {2, 1, 2, 3, 2, 4, 2, 5};

        int answer = majorityElement(nums);

        System.out.println("Majority element = " + answer);
    }

    private static int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
