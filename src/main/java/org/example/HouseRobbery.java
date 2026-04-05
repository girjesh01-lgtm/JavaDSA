package org.example;

public class HouseRobbery {

    public static void main(String[] args) {
        int[] houses = {2,7,9,6,1};
        //int[] houses = {1,2,3,1};
        //int[] houses = {2,1,1,2};

        int res = calculateMaxLoot(houses);
        System.out.println("res = "+ res);
    }

    private static int calculateMaxLoot(int[] houses) {
        //int[] mem = new int[houses.length];
        //Arrays.fill(mem, -1);
        //return calculateMaxTopDown(houses,0, mem);
        return calculateMaxBottomUp(houses);
    }

    private static int calculateMaxBottomUp(int[] houses) {
        int next = 0;
        int nextToNext = 0;
        for (int i=houses.length-1; i>=0; i--) {
            int temp = next;
            next = Math.max(houses[i]+nextToNext, next);
            nextToNext = temp;
        }
        return next;
    }

    private static int calculateMaxBottomUpCircularStreet(int[] houses) {
        if(houses.length == 1) {
            return houses[0];
        }
        return Math.max(robRange(houses, 0, houses.length-2), robRange(houses, 1, houses.length-1));
    }

    private static int robRange(int[] houses, int start, int end) {
        int prev1 = 0;
        int prev2 = 0;
        for (int i = end; i >= start; i--) {
            int curr = Math.max(houses[i]+ prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    private static int getFromMemory(int i, int[] mem, int length) {

        if(i < length && i >= 0) {
            return mem[i];
        }
        return 0;
    }


    private static int calculateMaxTopDown(int[] houses, int start, int[] mem) {


        if (start > houses.length-1) {
            return 0;
        }
        if(mem[start] != -1) {
            return mem[start];
        }

        int res = Math.max(
                calculateMaxTopDown(houses, start+1, mem),
                houses[start]+calculateMaxTopDown(houses, start+2, mem));
        mem[start] = res;
        return res;
    }

}

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */