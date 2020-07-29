package com.polykhel.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Repository {
    private final List<String> data;
    private final Map<String, Set<Integer>> invertedIndex;
    private SearchStrategy searchStrategy;

    public Repository(String fileName) throws IOException {
        this.data = Files.readAllLines(Path.of(fileName));
        invertedIndex = new HashMap<>();
        index();
    }

    private void index() {
        for (int i = 0; i < data.size(); i++) {
            final int index = i;
            String[] details = data.get(index).split(" ");
            for (String detail : details) {
                invertedIndex.compute(detail.toUpperCase(), (k, v) -> {
                    Set<Integer> indices = v;
                    if (v == null) {
                        indices = new HashSet<>();
                    }
                    indices.add(index);
                    return indices;
                });
            }
        }
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<String> search(String query) {
        return searchStrategy.search(data, invertedIndex, query);
    }

    public void printAll() {
        System.out.println(String.join("\n", data));
    }
}
