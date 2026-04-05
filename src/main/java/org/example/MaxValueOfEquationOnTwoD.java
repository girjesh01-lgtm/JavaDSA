package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxValueOfEquationOnTwoD {
    public static void main(String[] args) {
        //int[][] points = {{1,3},{2,0},{5,10},{6,-10}};
        //int k=1;
        //int[][] points = {{-19,9},{-15,-19},{-5,-8}};
        //int k=10;
        int[][] points = {{1,3}, {2,0}, {5,10}, {6,-10}};
        int k = 3;
        int result = findMaxValueOfEquation(points, k);
        System.out.println("result =  "+result);
        int result2 = findMaxValueOfEquationOptimized(points, k);
        System.out.println("result =  "+result2);
    }

    public static int findMaxValueOfEquationOptimized(int[][] points, int k) {

        // Deque will store: {x, (y - x)}
        Deque<int[]> dq = new ArrayDeque<>();

        int maxValue = Integer.MIN_VALUE;

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            // 1. Remove points that are out of range (xj - xi > k)
            while (!dq.isEmpty() && x - dq.peekFirst()[0] > k) {
                dq.pollFirst();
            }

            // 2. Use best candidate (front of deque)
            if (!dq.isEmpty()) {
                int best = dq.peekFirst()[1]; // (yi - xi)
                int value = best + y + x;     // (yi - xi) + (yj + xj)
                maxValue = Math.max(maxValue, value);
            }

            // 3. Maintain decreasing order of (y - x)
            int current = y - x;

            while (!dq.isEmpty() && dq.peekLast()[1] <= current) {
                dq.pollLast();
            }

            // 4. Add current point
            dq.offerLast(new int[]{x, current});
        }

        return maxValue;
    }


    public static int findMaxValueOfEquation(int[][] points, int k) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i + 1; j < points.length && points[j][0]-points[i][0] <= k; j++) {

                int[] pointA = new int[]{points[i][0], points[i][1]};
                int[] pointB = new int[]{points[j][0], points[j][1]};

                int value = pointA[1] + pointB[1] + Math.abs(pointA[0] - pointB[0]);
                maxValue = Math.max(value, maxValue);
                System.out.println("i=" + i + " j=" + j +
                        " (yi-xi)=" + (points[i][1] - points[i][0]));
            }
        }
        return maxValue;
    }
}


/*
You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.

Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.

It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.



Example 1:

Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
Output: 4
Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
Example 2:

Input: points = [[0,0],[3,0],[9,2]], k = 3
Output: 3
Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.


Constraints:

2 <= points.length <= 105
points[i].length == 2
-108 <= xi, yi <= 108
0 <= k <= 2 * 108
xi < xj for all 1 <= i < j <= points.length
xi form a strictly increasing sequence.
 */