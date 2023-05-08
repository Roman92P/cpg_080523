package com.for_comprehension.function.L4_stream;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class L4_StringDistinct {

    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        Stream.of("a", "bb", "ccc", "dd")
            .filter(s -> integers.add(s.length()))
            .forEach(System.out::println);
    }
}
