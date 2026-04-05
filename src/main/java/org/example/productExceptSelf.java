package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class productExceptSelf {

    public static void main(String[] args) {

        int[] input = {1,2,3,4};
        int[] result = getProductExceptSelf(input);
        //int[] res = getProductExceptSelfOptimised(input);
    }


    private static int[] getProductExceptSelfOptimised(int[] arr) {
        int[] result = new int[arr.length];



        Arrays.fill(result, 1);
        result[0] = 1;
        for (int i=1; i< arr.length; i++) {
            result[i] = result[i-1] * arr[i-1];
        }
        //printArray(result);
        int right=1;
        for (int i = arr.length-1; i >=0  ; i--) {
            result[i] = result[i] * right;
            right *= arr[i];
            printArray(result);
        }

        //printArray(result);

        return result;
    }

    private static int[] getProductExceptSelf(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        left[0] = 1;
        for (int i=1; i< arr.length; i++) {

            left[i] = left[i-1] * arr[i-1];

        }
        right[arr.length-1] = 1;
        for (int i = arr.length-2; i >=0  ; i--) {
            right[i] = right[i+1] * arr[i+1];
        }

        printArray(left);
        printArray(right);

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = left[i] * right[i];
        }

        printArray(res);
        return res;
    }

    private static void printArray(int[] arr) {

        System.out.println(" ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"  ");
        }
        System.out.println(" ");
    }
}
