package com.for_comprehension.function.E03;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class CollectorsExercises {

    /**
     * Collect elements to a {@link List} instance
     */
    static Function<List<String>, List<String>> L1_toList() {
        return list -> list.stream()
            .collect(toList());
    }

    /**
     * Collect elements to a {@link LinkedList} instance
     */
    static Function<List<String>, LinkedList<String>> L2_toLinkedList() {
        return list -> list.stream()
            .collect(toCollection(LinkedList::new));
    }

    /**
     * Collect elements to a {@link List} wrapped in {@link Collections#unmodifiableList(List)} instance
     */
    static Function<List<String>, List<String>> L3_unmodifiable() {
        return list -> list.stream().collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    /**
     * Collect elements to a {@link Map} instance with uppercased elements as keys and their corresponding lengths
     * as values and resolve potential collisions
     */
    static Function<List<String>, Map<String, Integer>> L4_toMap() {
        return list -> list.stream().collect(toMap(String::toUpperCase, String::length));
    }

    /**
     * Collect elements to a {@link TreeMap} instance with elements as keys and their corresponding lengths as
     * values and resolve potential collisions by picking any of the strings
     */
    static Function<List<String>, TreeMap<String, Integer>> L5_toTreeMap() {
        return list -> list.stream()
            .collect(toMap(s -> s, String::length, (o, o2) -> o, TreeMap::new));
    }

    /**
     * Collect Map elements to a JSON String instance
     * {@link Collectors#joining(CharSequence, CharSequence, CharSequence)}
     */
    static Function<Map<String, String>, String> L6_toJson() {
        return input -> input.entrySet().stream()
            .map(e -> String.format("\"%s\":\"%s\"", e.getKey(), e.getValue()))
            .collect(joining(",", "{", "}"));
    }

    /**
     * Group Strings of the same length {@link Collectors#groupingBy(Function)}
     */
    static Function<List<String>, Map<Integer, List<String>>> L7_groupStrings() {
        return input -> input.stream()
            .collect(groupingBy(String::length));
    }

    /**
     * Group Strings of the same length to a {@link TreeMap}
     */
    static Function<List<String>, TreeMap<Integer, List<String>>> L8_groupStrings() {
        return input -> input.stream()
            .collect(groupingBy(String::length, TreeMap::new, toList()));
    }

    /**
     * Group Strings of the same length into a comma-delimited String {@link Collectors#groupingBy(Function)}
     */
    static Function<List<String>, Map<Integer, String>> L9_groupStrings() {
        return input -> input.stream()
            .collect(groupingBy(String::length, joining(",")));
    }
}
