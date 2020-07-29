package com.polykhel.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchAny implements SearchStrategy {
    @Override
    public List<String> search(List<String> data, Map<String, Set<Integer>> invertedIndex, String query) {
        String[] queries = query.toUpperCase().split(" ");
        List<String> result = new ArrayList<>();
        for (String word : queries) {
            if (invertedIndex.containsKey(word)) {
                invertedIndex.get(word).forEach(index -> result.add(data.get(index)));
            }
        }
        return result;
    }
}
