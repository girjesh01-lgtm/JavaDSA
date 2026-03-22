package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RottenOranges {

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
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };

        int result = calculateTotalTime(input);
        System.out.println("result =  "+result);
    }

    public static int calculateTotalTime(int[][] box) {
        int totalMinutes = 0;
        int m = box.length;
        int n = box[0].length;

        Queue<Position> queue = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(box[i][j] == 2) {
                    Position pos = new Position(i, j);
                    queue.add(pos);
                }
                if(box[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        return bfs(box, queue, freshOranges);
    }

    public static int bfs(int[][] box, Queue<Position> queue, int freshOranges) {
        int minutes = 0;

        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int count = 0; count< queueSize; count++) {
                Position pos = queue.poll();
                int i = pos.row+1;
                int j = pos.col;

                if(i >= 0 && j >= 0 && i < box.length && j < box[0].length && box[i][j] == 1) {
                    queue.add(new Position(i,j));
                    box[i][j] = 2;
                    freshOranges--;
                }

                i = pos.row;
                j = pos.col+1;

                if(i >= 0 && j >= 0 && i < box.length && j < box[0].length && box[i][j] == 1) {
                    queue.add(new Position(i,j));
                    box[i][j] = 2;
                    freshOranges--;
                }

                i = pos.row-1;
                j = pos.col;

                if(i >= 0 && j >= 0 && i < box.length && j < box[0].length && box[i][j] == 1) {
                    queue.add(new Position(i,j));
                    box[i][j] = 2;
                    freshOranges--;
                }

                i = pos.row;
                j = pos.col-1;

                if(i >= 0 && j >= 0 && i < box.length && j < box[0].length && box[i][j] == 1) {
                    queue.add(new Position(i,j));
                    box[i][j] = 2;
                    freshOranges--;
                }
            }
            minutes += queue.isEmpty() ? 0 : 1;
        }


        return freshOranges == 0 ? minutes : -1;
    }
}

