package org.example;

import java.util.ArrayList;
import java.util.List;

public class PermutationsOfArray {

    /*
     * Problem: All Permutations Of Numbers In An Array
     *
     * Given an integer array nums where all elements are distinct, return all
     * possible permutations of the array.
     *
     * A permutation is a rearrangement of the elements in a particular order.
     *
     * You may return the permutations in any order.
     *
     * Example 1:
     * Input:
     * nums = [1, 2, 3]
     *
     * Output:
     * [
     *   [1, 2, 3],
     *   [1, 3, 2],
     *   [2, 1, 3],
     *   [2, 3, 1],
     *   [3, 1, 2],
     *   [3, 2, 1]
     * ]
     *
     * Example 2:
     * Input:
     * nums = [0, 1]
     *
     * Output:
     * [
     *   [0, 1],
     *   [1, 0]
     * ]
     *
     * Example 3:
     * Input:
     * nums = [1]
     *
     * Output:
     * [
     *   [1]
     * ]
     *
     * Constraints:
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * All integers in nums are unique.
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        List<List<Integer>> result = new ArrayList<>();


        boolean[] flags = new boolean[nums.length];

        permute(nums, 0, flags, result, new ArrayList<>());

        System.out.println(result);
    }

    private static void permute(int[] nums, int pos, boolean[] selected, List<List<Integer>> result, List<Integer> currentPermutation) {
        if(pos == nums.length) {
            result.add(List.copyOf(currentPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!selected[i]) {
                selected[i] = true;
                currentPermutation.add(nums[i]);
                permute(nums, pos+1, selected, result, currentPermutation);
                currentPermutation.remove(currentPermutation.size()-1);
                selected[i] = false;
            }
        }
    }
}
