package org.example;

import java.util.PriorityQueue;

public class KthLargestElement {

    public static void main(String[] args) {
        int [] input = new int[]{3,2,1,5,6,4};
        int result = getKthLargestElement(input, 5);

        System.out.println("result =    "+result);
    }

    public static int getKthLargestElement(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        if (k <= 0 || arr.length < k) {
            return -1;
        }


        for (int element:arr) {
            minHeap.offer(element);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

}
