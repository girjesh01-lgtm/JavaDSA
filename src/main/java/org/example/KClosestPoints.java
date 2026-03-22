package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KClosestPoints {
    public static void main(String[] args) {
        Point[] arr = new Point[] {
                new Point(1,3),
                new Point(5,-1),
                new Point(-2,4),
                new Point(-2,2)
        };
        int k=5;
        Point[] result = getClosestKPoints(arr, k);
        for (Point point : result) {
            System.out.println("Point --->  :"+point.x+"  "+point.y);
        }

    }

    public static Point[] getClosestKPoints(Point[] arr, int k) {

        if(arr.length < k) {
            return arr;
        }
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((a , b) -> {
            double aDistance = Math.sqrt(a.x*a.x + a.y*a.y);
            double bDistance = Math.sqrt(b.x*b.x + b.y*b.y);
            return Double.compare(bDistance, aDistance);
        });


        for (Point point : arr) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        Point[] result = new Point[k];
        for (int j = 0; j< k; j++) {
            result[j] = maxHeap.poll();
        }
        return result;
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
