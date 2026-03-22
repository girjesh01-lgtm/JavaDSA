package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MinReversalCityGraph {

    public static void main(String[] args) {
        /*
        int gNodes=4;
        List<Integer> gFrom = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> gTo = new ArrayList<>(Arrays.asList(4, 4, 4));

        List<Integer> res = countReverseEdges(gNodes, gFrom, gTo);
         */

        int gNodes = 4;
        List<Integer> gFrom = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> gTo = new ArrayList<>(Arrays.asList(2, 3, 4));

        List<Integer> res = countReverseEdges(gNodes, gFrom, gTo);


        for (Integer i : res) {
            System.out.println(i);
        }

    }


    public static List<Integer> countReverseEdges(int gNodes, List<Integer> gFrom, List<Integer> gTo) {

        List<List<int[]>> graph = new ArrayList<>();
        int[] ans = new int[gNodes + 1];

        for(int i = 0; i <= gNodes; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < gFrom.size(); i++){
            int u = gFrom.get(i);
            int v = gTo.get(i);

            graph.get(u).add(new int[]{v,0});
            graph.get(v).add(new int[]{u,1});
        }

        // -------- First DFS (calculate answer for node 1) --------
        boolean[] visited = new boolean[gNodes + 1];
        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        while(!stack.isEmpty()){
            int node = stack.pop();
            visited[node] = true;

            for(int[] edge : graph.get(node)){
                int next = edge[0];
                int cost = edge[1];

                if(!visited[next]){
                    ans[1] += cost;
                    stack.push(next);
                }
            }
        }

        // -------- Second DFS (reroot calculation) --------
        Arrays.fill(visited,false);
        stack.push(1);
        visited[1] = true;

        while(!stack.isEmpty()){

            int node = stack.pop();

            for(int[] edge : graph.get(node)){

                int next = edge[0];
                int cost = edge[1];

                if(!visited[next]){

                    if(cost == 0)
                        ans[next] = ans[node] + 1;
                    else
                        ans[next] = ans[node] - 1;

                    visited[next] = true;
                    stack.push(next);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 1; i <= gNodes; i++)
            result.add(ans[i]);

        return result;
    }
}
