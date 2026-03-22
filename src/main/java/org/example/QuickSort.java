package org.example;

public class QuickSort {

    public static void main(String[] args) {
        /*
        int[] arr = new int[]{5,1,3,2,7,6,4};
        quickSort(arr, 0, arr.length-1);

        for (int  ele : arr) {
            System.out.print(ele+"  ");
        }

         */

        int[] input = new int[]{5,1,3,2,7,6,4};

        System.out.println("kth Largest element = "+findKthLargestElement(input, 5));
    }

    public static void quickSort(int[] arr, int startIndex, int lastIndex) {
        if(startIndex >= lastIndex) {
            return;
        }
        int pivot = lastIndex;
        int i=startIndex;
        for (int j=startIndex; j < pivot; j++) {
            if(arr[j] < arr[pivot]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = arr[pivot];
        arr[pivot] = temp;
        pivot = i;
        quickSort(arr, startIndex, pivot-1);
        quickSort(arr, pivot+1, lastIndex);
    }

    public static int findKthLargestElement(int[] arr, int k) {
        int requiredIndex = arr.length-k;
        return quickSelect(arr, 0, arr.length-1, requiredIndex);

    }

    public static int quickSelect(int[] arr, int startIndex, int lastIndex, int requiredIndex) {
        if (startIndex == lastIndex) {
            return arr[startIndex];
        }
        if(startIndex < lastIndex) {
            int i = startIndex;
            int pivot = lastIndex;
            for (int j=startIndex; j< lastIndex; j++) {
                if (arr[j] < arr[pivot]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                }
            }
            int temp = arr[i];
            arr[i] = arr[pivot];
            arr[pivot] = temp;
            pivot = i;

            if(requiredIndex == pivot) {
                return arr[pivot];
            }else if(requiredIndex < pivot) {
                return quickSelect(arr, startIndex, pivot-1, requiredIndex);
            } else {
                return quickSelect(arr, pivot+1, lastIndex, requiredIndex);
            }
        }
        return -1;
    }
}
