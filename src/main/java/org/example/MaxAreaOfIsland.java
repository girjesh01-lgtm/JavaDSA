package org.example;

public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };

        int[][] input = {
                {0,0,1,0,0},
                {1,1,1,0,0},
                {0,1,0,0,1},
                {0,0,0,1,1}
        };

        System.out.println("grid max area == " + getMaxArea(grid));
        System.out.println("input max area == " + getMaxArea(input));

        System.out.println("my implementation");

        grid = new int[][] {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };

        input = new int[][]{
                {0,0,1,0,0},
                {1,1,1,0,0},
                {0,1,0,0,1},
                {0,0,0,1,1}
        };


        System.out.println("grid max area == " + getMaxAreaMine(grid));
        System.out.println("input max area == " + getMaxAreaMine(input));
    }

    public static int getMaxAreaMine(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int maxSize = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                int[] maxArea = {0};
                if(grid[i][j] == 1) {
                    dfsMine(grid, i,j, maxArea);
                }

                maxSize = Math.max(maxArea[0], maxSize);            }
        }


        return maxSize;
    }

    public static void dfsMine(int[][] grid,int i,int j, int[] maxArea) {

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }

        maxArea[0] += 1;
        grid[i][j] = 0;
        dfsMine(grid, i+1,j, maxArea);
        dfsMine(grid, i-1,j, maxArea);
        dfsMine(grid, i,j+1, maxArea);
        dfsMine(grid, i,j-1, maxArea);

    }

    public static int getMaxArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        for (int i=0; i < m; i++) {
            for (int j=0; j < n; j++) {
                int maxSize = 0;
                if(grid[i][j] == 1) {
                    maxSize = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, maxSize);
                }
            }
        }
        return maxArea;
    }

    public static int dfs(int[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        return 1 + dfs(grid, i+1, j) + dfs(grid, i-1, j) + dfs(grid, i, j+1) + dfs(grid, i, j-1);
    }

}
