package org.example;/*
PROBLEM: Move Zeros to Start and Ones to End While Preserving Order

Given an integer array arr[], rearrange the array such that:

1. All 0s appear at the beginning of the array.
2. All 1s appear at the end of the array.
3. All remaining elements (elements other than 0 and 1)
   must retain their original relative order.

Example 1:
Input:
arr = [5, 0, 3, 1, 0, 7, 1, 9]

Output:
[0, 0, 5, 3, 7, 9, 1, 1]

Explanation:
- 0s moved to front
- 1s moved to end
- Remaining elements [5,3,7,9] kept in same order

--------------------------------------------------

Example 2:
Input:
arr = [1, 2, 0, 4, 1, 6]

Output:
[0, 2, 4, 6, 1, 1]

--------------------------------------------------

Constraints:
1 <= arr.length <= 10^5
-10^9 <= arr[i] <= 10^9

--------------------------------------------------

Follow Ups:
1. Solve in O(n) using extra space.
2. Solve in-place.
3. Preserve relative order while solving in-place.

--------------------------------------------------

Concepts:
- Two Pointers
- Stable Partitioning
- Array Manipulation
- In-place Rearrangement
- Time vs Space Tradeoff
*/


public class RearrangeArray {
    public static void main(String[] args) {
        int[] input1 = {1, 2, 0, 4, 1, 6};
        int[] input2 = {5, 0, 3, 1, 0, 7, 1, 9};


        int[] result = rearrange(input2);
        System.out.println(" ");
        for(int i=0; i<result.length; i++) {
            System.out.print(result[i] + "   ");
        }

    }


    public static int[] rearrange(int[] arr) {

        int n = arr.length;

        int zeroCount = 0;
        int oneCount = 0;

        // Count zeros and ones
        for (int num : arr) {
            if (num == 0) zeroCount++;
            else if (num == 1) oneCount++;
        }

        // Move non-zero elements to the right
        int write = n - 1;

        for (int read = n - 1; read >= 0; read--) {

            if (arr[read] != 0) {
                arr[write--] = arr[read];
            }
        }

        // Fill zeros at front
        for (int i = 0; i < zeroCount; i++) {
            arr[i] = 0;
        }

        // Move non-one elements to the left
        write = zeroCount;

        for (int read = zeroCount; read < n; read++) {

            if (arr[read] != 1) {
                arr[write++] = arr[read];
            }
        }

        // Fill ones at end
        for (int i = n - oneCount; i < n; i++) {
            arr[i] = 1;
        }

        return arr;
    }

    public static int[] reArrange(int[] arr) {
        int arrLen = arr.length;

        int zeros = 0;
        int ones = 0;

        // counting zeros and ones
        for (int i = 0; i < arrLen; i++) {

            if (arr[i] == 0) {
                zeros++;
            }
            if (arr[i] == 1) {
                ones++;
            }
        }

        // moving zeros to the front
        int current = arrLen-1;
        for(int j = current; j >= 0; j--) {
            if(arr[j] != 0 && j == current) {
                current--;
            }
            else if (arr[j] != 0 && j != current) {
                //move arr[j] to arr[current]
                arr[current] = arr[j];
                current--;
            }
        }

        for (int i=0; i<zeros; i++) {
            arr[i] =0;
        }

        // moving ones to the back/end
        current = zeros;
        for(int j = current; j < arrLen; j++) {
            if(arr[j] != 1 && j == current) {
                current++;
            }
            else if (arr[j] != 1 && j != current) {
                //move arr[j] to arr[current]
                arr[current] = arr[j];
                current++;
            }
        }

        for (int i=arrLen-1; i >= arrLen-ones; i--) {
            arr[i] =1;
        }
        return arr;
    }
}