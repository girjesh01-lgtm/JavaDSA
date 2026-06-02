package org.example;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /*
     * Problem: N-Queens
     *
     * The n-queens puzzle is the problem of placing n queens on an n x n
     * chessboard such that no two queens attack each other.
     *
     * A queen can attack another queen if they share the same:
     * - row
     * - column
     * - diagonal
     *
     * Return all distinct solutions to the n-queens puzzle.
     *
     * Each solution contains a board configuration represented as a list of
     * strings, where:
     * - 'Q' indicates a queen
     * - '.' indicates an empty space
     *
     * Example 1:
     * Input:
     * n = 4
     *
     * Output:
     * [
     *   [".Q..", "...Q", "Q...", "..Q."],
     *   ["..Q.", "Q...", "...Q", ".Q.."]
     * ]
     *
     * Explanation:
     * There are 2 distinct ways to place 4 queens on a 4 x 4 board.
     *
     * Example 2:
     * Input:
     * n = 1
     *
     * Output:
     * [["Q"]]
     *
     * Constraints:
     * 1 <= n <= 9
     */
    public static void main(String[] args) {
        int n = 8;

        List<List<String>> answer = solveNQueens(n);
        System.out.println(answer.size());
        for (List<String> solution : answer) {
            System.out.println();
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
            System.out.println("  ----   ");
            System.out.println();
        }
    }

    private static List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = '.';
            }
        }

        List<List<String>> result = new ArrayList<>();
        backtrack(board, 0, result, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
        return result;
    }

    private static void backtrack(char[][] board, int row, List<List<String>> result, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (row == board.length) {
            result.add(buildBoard(board));
            return;
        }

        int n = board.length;
        for (int col = 0; col < n; col++) {
            if (!cols[col] && !d1[row + col] && !d2[row - col + n - 1]) {
                board[row][col] = 'Q';
                cols[col] = true;
                d1[row + col] = true;
                d2[row - col + n - 1] = true;
                backtrack(board, row + 1, result, cols, d1, d2);
                board[row][col] = '.';
                cols[col] = false;
                d1[row + col] = false;
                d2[row - col + n - 1] = false;
            }
        }
    }

    private static List<String> buildBoard(char[][] board) {
        List<String> currentBoard = new ArrayList<>();

        for (char[] row : board) {
            currentBoard.add(new String(row));
        }

        return currentBoard;
    }
}
