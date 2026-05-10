package org.example;


import java.util.*;

public class SentenseFromSynonyms {

    public static void main(String[] args) {
        List<List<String>> synonyms = new ArrayList<>();
        synonyms.add(new ArrayList<>(Arrays.asList("happy", "joy")));
        synonyms.add(new ArrayList<>(Arrays.asList("sad", "sorrow")));
        synonyms.add(new ArrayList<>(Arrays.asList("joy", "cheerful")));

        String inputText = "I am happy today but was sad yesterday";
        System.out.println(generateSentences(synonyms, inputText));
    }

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {

        // Step 1: build graph
        Map<String, Set<String>> graph = new HashMap<>();

        for (List<String> pair : synonyms) {
            String a = pair.get(0);
            String b = pair.get(1);

            graph.computeIfAbsent(a, k -> new HashSet<>()).add(b);
            graph.computeIfAbsent(b, k -> new HashSet<>()).add(a);
        }

        // Step 2: find connected components
        Map<String, List<String>> map = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String word : graph.keySet()) {
            if (!visited.contains(word)) {
                List<String> list = new ArrayList<>();
                dfs(word, graph, visited, list);
                Collections.sort(list);

                for (String w : list) {
                    map.put(w, list);
                }
            }
        }

        // Step 3: backtracking
        List<String> result = new ArrayList<>();
        String[] words = text.split(" ");

        backtrack(words, 0, map, new ArrayList<>(), result);

        Collections.sort(result);
        return result;
    }

    private static void dfs(String word, Map<String, Set<String>> graph,
                     Set<String> visited, List<String> list) {

        visited.add(word);
        list.add(word);

        for (String nei : graph.getOrDefault(word, new HashSet<>())) {
            if (!visited.contains(nei)) {
                dfs(nei, graph, visited, list);
            }
        }
    }

    private static void backtrack(String[] words, int index,
                           Map<String, List<String>> map,
                           List<String> path,
                           List<String> result) {

        if (index == words.length) {
            result.add(String.join(" ", path));
            return;
        }

        String word = words[index];

        if (map.containsKey(word)) {
            for (String syn : map.get(word)) {
                path.add(syn);
                backtrack(words, index + 1, map, path, result);
                path.remove(path.size() - 1);
            }
        } else {
            path.add(word);
            backtrack(words, index + 1, map, path, result);
            path.remove(path.size() - 1);
        }
    }
}
