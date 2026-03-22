package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class TopKFrequentElements {

    public static void main(String[] args) {
        int [] input = new int[]{1, 1, 1, 2, 2, 3, 7, 7, 7, 7};
        int[] result = getTopKFrequentElement(input, 3);
        for (int val : result){
            System.out.println(val);
        }
    }

    public static int[] getTopKFrequentElement(int[] input, int k){

        Map<Integer, Integer> mapping = new HashMap<>();


        for(int i = 0; i < input.length; i++) {
            int currentElement = input[i];
            mapping.put(currentElement, mapping.getOrDefault(currentElement, 0)+1);
        }

        PriorityQueue<Integer> minheap = new PriorityQueue<>((a,b) -> Integer.compare(mapping.get(a) ,mapping.get(b)));

        for (int item : mapping.keySet()) {
            minheap.offer(item);
            if(minheap.size() > k) {
                minheap.poll();
            }
        }

        int[] result = new int[k];
        for (int i=k-1; i>=0; i--) {
            result[i] = minheap.poll();// here check the mapping for min value and add it to the result array and delete it from the heap
        }
        return result;
    }
}
