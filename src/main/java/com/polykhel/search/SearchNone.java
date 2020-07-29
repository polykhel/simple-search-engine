package com.polykhel.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchNone implements SearchStrategy {
    @Override
    public List<String> search(List<String> data, Map<String, Set<Integer>> invertedIndex, String query) {
        String[] queries = query.toUpperCase().split(" ");
        Set<Integer> lineNumbers = new HashSet<>();
        for (String s : queries) {
            if (invertedIndex.containsKey(s)) {
                lineNumbers.addAll(invertedIndex.get(s));
            }
        }
        return invertedIndex.values()
                .stream()
                .flatMap(Collection::stream)
                .distinct()
                .filter(i -> !lineNumbers.contains(i))
                .map(data::get)
                .collect(Collectors.toList());
    }
}
