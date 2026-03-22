package org.example;

import java.util.Arrays;

public class MinimumShifts {

    public static void main(String[] args) {
        //String[] mat = new String[]{"1010", "1110"};
        String[] mat = new String[] {"0101", "1010", "0100", "0001"};
        //System.out.println("/n ------- "+findMinimumShifts(mat));
        System.out.println("/n ------- "+findMinimumShiftsOptomized(mat));
    }

    public static int findMinimumShifts(String[] mat) {
        int minCost = Integer.MAX_VALUE;

        int n = mat.length;
        int m = mat[0].length();

        int[] totalCost = new int[m];

        for(int column=0; column < m; column++) {
            for (int row=0; row < n; row++) {
                boolean hasOne = false; // to check if the row is containing at least one '1'
                int minDist = Integer.MAX_VALUE;
                for(int i=0; i < m; i++)
                {
                    char currentChar =  mat[row].charAt(i);
                    if(Character.getNumericValue(currentChar) == 1) {
                        hasOne=true;
                        int diff = Math.abs(column - i);
                        int dist = Math.min(diff, m - diff);
                        minDist = Math.min(minDist, dist);
                    }
                }
                if(!hasOne) {
                    System.out.print("hello");
                    return -1;
                }
                totalCost[column] += minDist;
            }
        }
        for(int column=0; column < mat[0].length(); column++) {
            minCost =  Math.min(totalCost[column], minCost);
        }
        return minCost;
    }

    public static int findMinimumShiftsOptomized(String[] mat) {

        int minCost = Integer.MAX_VALUE;

        int n = mat.length;
        int m = mat[0].length();

        int[][] distCol = new int[n][m];

        for (int row=0; row < n; row++) {
            int[] colDistLeft = new int[m];
            Arrays.fill(colDistLeft, Integer.MAX_VALUE);
            boolean hasOne = false; // to check if the row is containing at least one '1'
            int lastOne = -1;
            for(int i=0; i < 2*m; i++) {
                int column = i % m;
                char currentChar =  mat[row].charAt(column);
                if(currentChar == '1') {
                    hasOne=true;
                    lastOne = i;
                }

                if(lastOne != -1) {
                    colDistLeft[column] = Math.min(colDistLeft[column], i-lastOne);
                }

            }
            if (!hasOne) {
                return -1;
            }

            int[] colDistRight = new int[m];
            Arrays.fill(colDistRight, Integer.MAX_VALUE);
            lastOne = -1;
            for(int i=2*m-1; i >=0; i--) {
                int column = i%m;
                char currentChar =  mat[row].charAt(column);
                if(currentChar == '1') {
                    lastOne = i;
                }

                if(lastOne != -1) {
                    colDistRight[column] = Math.min(colDistRight[column], lastOne-i);
                }
            }
            for(int column=0; column < m; column++) {
                distCol[row][column] = Math.min(colDistRight[column], colDistLeft[column]);
            }

        }

        int[] colDist = new int[m];
        for (int column=0; column < m; column++) {
            int totaldist = 0;
            for(int row=0; row < n; row++) {
                System.out.print(distCol[row][column]+"    ");
                totaldist+= distCol[row][column];
            }
            System.out.println("  ");
            colDist[column] =  totaldist;
        }
        for (int column=0; column < m; column++) {
            //System.out.print(colDist[column]+"   ");
            minCost = Math.min(colDist[column], minCost);
        }
        return minCost;
    }
}
