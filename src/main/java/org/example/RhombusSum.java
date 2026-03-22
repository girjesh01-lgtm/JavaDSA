package org.example;

import java.util.TreeSet;

public class RhombusSum {

    public static void main(String[] args) {

        int[][] input = new int[][]{{3,4,5,1,3},{3,3,4,2,3},{20,30,200,40,10},{1,5,5,4,1},{4,3,2,2,5}};
        int[] result = getBiggestThree(input);

        for (int i=0; i < result.length; i++) {
            System.out.print(result[i]+"  ");
        }
    }

    public static int[] getBiggestThree(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int k = 0;
                while (true) {
                    if (k == 0) {
                        add(set, grid[r][c]);
                        k++;
                        continue;
                    }

                    if (r + 2 * k >= m || c - k < 0 || c + k >= n) {
                        break;
                    }

                    int sum = 0;

                    for(int i = 0; i < k; i++) {
                        sum += grid[r + i][c + i];
                    }
                    for(int i = 0; i < k; i++) {
                        sum += grid[r + k + i][c + k - i];
                    }
                    for(int i = 0; i < k; i++) {
                        sum += grid[r +2*k -i][c - i];
                    }
                    for(int i = 0; i < k; i++) {
                        sum += grid[r + k - i][c - k + i];
                    }

                    add(set, sum);
                    k++;
                }
            }
        }

        int size = set.size();
        int[] res = new int[size];

        for(int i = 0; i < size; i++) {
            res[i] = set.pollLast();
        }


        return res;
    }

    public static void add(TreeSet set, int sum) {
        set.add(sum);

        if (set.size() > 3) {
            set.pollFirst();
        }
    }


}
