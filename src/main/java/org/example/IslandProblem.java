package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class IslandProblem {

    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };

        System.out.println(numIslands(grid));
        //getNumberOfIslands

        grid = new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };
        System.out.println("bfs result = "+getNumberOfIslands(grid));


    }

    public static int numIslands(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 1) {
                    count++;
                    dfs(grid, i, j);
                }

            }
        }

        return count;
    }

    private static void dfs(int[][] grid, int i, int j) {
        //System.out.println("i   = "+i+"  j  ="+j);
        // Base conditions
        if (i < 0 || j < 0 ||
                i >= grid.length ||
                j >= grid[0].length ||
                grid[i][j] == 0) {
            return;
        }

        // Mark as visited
        grid[i][j] = 0;

        // Explore 4 directions
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static int getNumberOfIslands(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Queue<Position> queue = new LinkedList<>();
        int count = 0;
        for(int i=0; i < m; i++) {
            for(int j=0; j < n; j++) {
                if(grid[i][j] == 1) {
                    count++;
                    queue.add(new Position(i,j));
                    bfs(grid, queue);
                }
            }
        }
        return count;
    }

    public static void bfs(int[][] grid, Queue<Position> queue) {
        int m = grid.length;
        int n = grid[0].length;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            if (pos.row >= 0 && pos.col >= 0 && pos.row < m && pos.col < n && grid[pos.row][pos.col] == 1) {

                grid[pos.row][pos.col] = 0;
                queue.add(new Position(pos.row + 1, pos.col));
                queue.add(new Position(pos.row, pos.col + 1));
                queue.add(new Position(pos.row - 1, pos.col));
                queue.add(new Position(pos.row, pos.col - 1));
            }
        }
    }

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

