package org.example;

import java.util.Arrays;
import java.util.List;

public class GridCut {

    public static void main(String[] args) {
        int[][] grid = {{1,4},{2,3}};
        boolean result = canPartitionGrid(grid);
        boolean res2 = canPartitionGridWithoutExtraSpace(grid);
        System.out.println(result);
        System.out.println(res2);
    }

    private static boolean canPartitionGridWithoutExtraSpace(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int totalSum = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }
        if (totalSum % 2 != 0) {
            return false;
        }

        int totalSumForRows = totalSum;
        int totalSumForCol = totalSum;

        int rowsSum = 0;
        for (int i = 0; i < m-1; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            rowsSum += rowSum;
            totalSumForRows -= rowSum;
            if(rowsSum == totalSumForRows) {
                return true;
            }
        }

        int colsSum = 0;
        for (int i = 0; i < n-1; i++) {
            int colSum = 0;
            for (int j = 0; j < m; j++) {
                colSum += grid[j][i];
            }
            colsSum += colSum;
            totalSumForCol -= colSum;
            if(colsSum == totalSumForCol) {
                return true;
            }
        }
        return false;
    }

    public static boolean canPartitionGrid(int[][] grid) {
        //m -> rows
        //n -> columns
        int m = grid.length;
        int n = grid[0].length;

        long totalsum = 0;
        long[] horizSum = new long[m];
        long[] vertSum = new long[n];

        Arrays.fill(horizSum, 0);
        Arrays.fill(vertSum, 0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalsum += grid[i][j];
                horizSum[i] += grid[i][j];
                vertSum[j] += grid[i][j];
            }
        }

        if(totalsum % 2 != 0) {
            return false;
        }

        boolean horizCut = checkIfCutIsPossible(horizSum);
        boolean vertCut = checkIfCutIsPossible(vertSum);

        return horizCut || vertCut;
    }

    public static boolean checkIfCutIsPossible(long[] arr) {

        long leftSum = 0;
        long rightSum = 0;

        for (int count = 0; count < arr.length; count++) {
            rightSum += arr[count];
        }

        for (int i = 0; i < arr.length - 1; i++) {
            leftSum += arr[i];
            rightSum -= arr[i];
            if (leftSum == rightSum) {
                return true;
            }
        }
        return false;
    }

    public static void doNothing() {
        var name = "Girjesh";
        var strengths = List.of(1,2,3);

    }

}


/*
3546. Equal Sum Grid Partition I
Medium
Topics
Hint
You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:

Each of the two resulting sections formed by the cut is non-empty.
The sum of the elements in both sections is equal.
Return true if such a partition exists; otherwise return false.



Example 1:

Input: grid = [[1,4],[2,3]]

Output: true

Explanation:



A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.

Example 2:

Input: grid = [[1,3],[2,4]]

Output: false

Explanation:

No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.



Constraints:

1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105
 */