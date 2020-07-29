package com.polykhel.search;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchStrategy {
    List<String> search(List<String> data, Map<String, Set<Integer>> invertedIndex, String query);
}
