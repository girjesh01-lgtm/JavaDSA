package org.example;

import java.util.PriorityQueue;

public class MouseRewards {
    public static void main(String[] args) {

        /*
        int k=2;
        int[] reward1 = {1,1,3,4};
        int[] reward2 = {4,4,1,1};
         */

        int k=2;
        int[] reward1 = {1,1};
        int[] reward2 = {1,1};

        int res =  getTheMaxRewards(reward1, reward2, k);
        System.out.println(res);
    }

    private static int getTheMaxRewards(int[] reward1, int[] reward2, int k) {
        if(k > reward1.length) {
            return -1;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> {
            return Integer.compare(reward1[a]-reward2[a], reward1[b]-reward2[b]);
        });

        int maxPoints = 0;

        for (int i=0; i<reward1.length; i++) {
            maxPoints += reward2[i];
            minHeap.offer(i);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        for(int j = 0; j<k; j++) {
            int max1Index = minHeap.poll();
            maxPoints += (reward1[max1Index] - reward2[max1Index]);
        }

        return maxPoints;
    }


}

/*
There are two mice and n different types of cheese, each type of cheese should be eaten by exactly one mouse.

A point of the cheese with index i (0-indexed) is:

reward1[i] if the first mouse eats it.
reward2[i] if the second mouse eats it.
You are given a positive integer array reward1, a positive integer array reward2, and a non-negative integer k.

Return the maximum points the mice can achieve if the first mouse eats exactly k types of cheese.



Example 1:

Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
Output: 15
Explanation: In this example, the first mouse eats the 2nd (0-indexed) and the 3rd types of cheese, and the second mouse eats the 0th and the 1st types of cheese.
The total points are 4 + 4 + 3 + 4 = 15.
It can be proven that 15 is the maximum total points that the mice can achieve.
Example 2:

Input: reward1 = [1,1], reward2 = [1,1], k = 2
Output: 2
Explanation: In this example, the first mouse eats the 0th (0-indexed) and 1st types of cheese, and the second mouse does not eat any cheese.
The total points are 1 + 1 = 2.
It can be proven that 2 is the maximum total points that the mice can achieve.


Constraints:

1 <= n == reward1.length == reward2.length <= 105
1 <= reward1[i], reward2[i] <= 1000
0 <= k <= n
 */