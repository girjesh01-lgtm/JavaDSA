package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinMoveSegment {
    public static void main(String[] args) {
        //List<Integer> segments = new ArrayList<>(Arrays.asList(1,1,0,1));
        //List<Integer> segments = new ArrayList<>(Arrays.asList( 1,1,1,1,1));
        List<Integer> segments = new ArrayList<>(Arrays.asList(0,0,0,0,0,1,1,1,1,1,1));

        int result = calculateMinMove(segments);

        System.out.print("result = "+ result);

    }

    public static int calculateMinMove(List<Integer> segments) {

        int totalPoints = 0;

        for ( Integer value : segments) {
            totalPoints += value == 0 ? -1 : 1;
        }

        if (totalPoints < 0) return 0;

        int pointsOfA = 0;
        int pointsOfB = totalPoints;


        for (int i=0; i<segments.size(); i++) {
            int delta =  segments.get(i) == 0 ? -1 : 1;
            pointsOfA += delta;
            pointsOfB -= delta;
            if(pointsOfA > pointsOfB){
                return i+1;
            }

        }

        return 0;
    }
}
