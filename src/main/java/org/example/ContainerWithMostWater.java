package org.example;

public class ContainerWithMostWater {

    /*
     * Problem: Container With Most Water
     *
     * You are given an integer array heights where each element represents the
     * height of a vertical line drawn at that index.
     *
     * Choose any two lines such that together with the x-axis they form a
     * container. Return the maximum amount of water the container can store.
     *
     * Area formula:
     * area = width * height
     *
     * where:
     * width = distance between the two chosen lines
     * height = the shorter of the two lines
     *
     * The shorter line determines the height because water overflows from the
     * shorter side.
     *
     * Example 1:
     * Input: heights = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     *
     * Explanation:
     * Choose the lines at index 1 and index 8.
     * width = 8 - 1 = 7
     * height = min(8, 7) = 7
     * area = 7 * 7 = 49
     *
     * Example 2:
     * Input: heights = [1,1]
     * Output: 1
     *
     * Explanation:
     * Choose index 0 and index 1.
     * width = 1
     * height = min(1, 1) = 1
     * area = 1 * 1 = 1
     *
     * Example 3:
     * Input: heights = [4,3,2,1,4]
     * Output: 16
     *
     * Explanation:
     * Choose index 0 and index 4.
     * width = 4
     * height = min(4, 4) = 4
     * area = 4 * 4 = 16
     *
     * Constraints:
     * 2 <= heights.length <= 100000
     * 0 <= heights[i] <= 10000
     */
    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int answer = maxArea(heights);

        System.out.println("Maximum area = " + answer);
    }

    private static int maxArea(int[] heights) {

        // edge case check for if the array is null of have ony one element in it
        if (heights == null || heights.length < 2) return 0;

        int maxArea = 0;
        int left = 0;
        int right = heights.length-1;

        while (left < right) {
            int area = (right - left) * Math.min(heights[left], heights[right]);

            maxArea = Math.max(maxArea, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
