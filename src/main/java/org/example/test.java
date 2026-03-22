/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
        ["1","1","1","1","0"],
        ["1","1","0","1","0"],
        ["1","1","0","0","0"],
        ["0","0","0","0","0"]
        ]
Output: 1
Example 2:

Input: grid = [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
        ]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/
package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class test {


    public static void main(String[] args) {
        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0}, {0,0,1,0,0}, {1,1,0,0,0}, {0,0,0,1,1}};
        Integer count = 0;
        int result = getIslands(grid, 0,0, false, count);
        System.out.println(result);
    }

    public static int getIslands(int[][] grid, int i, int j , boolean flag, Integer count) {
        System.out.println("count = "+count);
        if (i < grid.length && j < grid[0].length) {


            if (grid[i][j] == 1) {
                if (!flag) {
                    count += 1;
                }
                flag = true;


            } else {
                flag = false;
            }
            // check the right next value

            getIslands(grid, i + 1, j, flag, count);

            // check the down next value
            getIslands(grid, i, j + 1, flag, count);


        }
        return count;

    }

}
