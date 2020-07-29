package com.polykhel.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchAll implements SearchStrategy {

    @Override
    public List<String> search(List<String> data, Map<String, Set<Integer>> invertedIndex, String query) {
        String[] queries = query.toUpperCase().split(" ");
        Set<Integer> lineNumbers = new HashSet<>();
        for (int i = 0; i < queries.length; i++) {
            if (invertedIndex.containsKey(queries[i])) {
                Set<Integer> lines = invertedIndex.get(queries[i]);

                if (i == 0) {
                    lineNumbers.addAll(lines);
                }

                lineNumbers.retainAll(lines);
            }
        }
        return lineNumbers.stream().map(data::get).collect(Collectors.toList());
    }
}
