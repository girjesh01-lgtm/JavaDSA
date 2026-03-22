package org.example;

import java.util.*;


public class DistanceToNearestZero {

    public static class Position {

        public int row;
        public int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    public static void main(String[] args) {
        int[][] input = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };




        int[][] result = calculaateMinimumDistance(input);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]+"  ");
            }
            System.out.println(" ");
        }


    }

    public static int[][] calculaateMinimumDistance(int[][] box) {

        Queue<Position> queue = new LinkedList<>();
        int m = box.length;
        int n = box[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(box[i][j] == 0) {
                    Position pos = new Position(i, j);
                    queue.add(pos);
                    result[i][j] = 0;
                } else {
                    result[i][j] = -1;
                }
            }
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while(!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int[] d : dirs) {
                int ni = pos.row + d[0];
                int nj = pos.col + d[1];

                if (ni >= 0 && nj >= 0 && ni < m && nj < n && (result[ni][nj] == -1)) {
                    result[ni][nj] = result[pos.row][pos.col] + 1;
                    queue.add(new Position(ni, nj));
                }
            }


        }
        return result;
    }
}
