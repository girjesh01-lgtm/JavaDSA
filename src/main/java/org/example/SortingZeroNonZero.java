package org.example;

import java.sql.Array;
import java.util.ArrayList;

public class SortingZeroNonZero {

    public static void main(String[] args) {
        int[] input = new int[]{1,0,2,0,5,0,0,3};


        System.out.println("Input : ");
        for (int i = 0; i< input.length; i++) {
            System.out.print(input[i]);
            System.out.print("  ");
        }

        int[] output = sortArray(input);

        System.out.println("\nOutput : ");
        for (int i = 0; i< output.length; i++) {
            System.out.print(output[i]);
            System.out.print("  ");
        }

    }

    public static int[] sortArray(int[] arr) {

        int validZeroIndex=0;
        for (int i=0; i< arr.length; i++) {
            if (arr[i] != 0 ) {
                    arr[validZeroIndex++] =  arr[i];
                    //arr[i] =  0;
            }
        }

        for (int j = validZeroIndex; j < arr.length; j++) {
            arr[j] = 0;
        }

        return arr;
    }
}
