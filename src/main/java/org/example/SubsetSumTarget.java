package org.example;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumTarget {

    /*
     * Problem: Subset Of Elements With Sum Equal To Target
     *
     * Given an integer array nums and an integer target, return all subsets whose
     * sum is exactly equal to target.
     *
     * A subset may contain any combination of elements from the array, but each
     * array element can be used at most once.
     *
     * You may return the subsets in any order.
     *
     * Example 1:
     * Input:
     * nums = [1, 2, 3]
     * target = 3
     *
     * Output:
     * [[1, 2], [3]]
     *
     * Example 2:
     * Input:
     * nums = [2, 4, 6, 10]
     * target = 16
     *
     * Output:
     * [[2, 4, 10], [6, 10]]
     *
     * Notes:
     * - This version assumes all array elements are distinct.
     * - Each element can be picked at most once.
     * - If no valid subset exists, return an empty list.
     */
    public static void main(String[] args) {
        //int[] nums = {1, 2, 3};
        //int target = 3;

        int[] nums = {10, 20, 30, 40, 50};
        int target = 60;

        List<List<Integer>> answer = subsetsWithTargetSum(nums, target);

        System.out.println(answer);
    }

    private static List<List<Integer>> subsetsWithTargetSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, target, new ArrayList<Integer>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int i, int target, List<Integer> subset, List<List<Integer>> result) {
        if(target < 0) {
            return;
        }
        if(i == nums.length) {
            if(target == 0)
                result.add(List.copyOf(subset));
            return;
        }
        // consider the current index i
        subset.add(nums[i]);
        backtrack(nums, i+1, target-nums[i], subset, result);
        subset.remove(subset.size()-1);

        // do not consider the current index i
        backtrack(nums, i+1, target, subset, result);
    }
}
