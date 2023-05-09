package com.for_comprehension.function.L4_stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L6_StreamTerminal {

    public static void main(String[] args) {
        List<Integer> c1 = Stream.of(1, 2, 3).collect(Collectors.toList());
        Set<Integer> c2 = Stream.of(1, 2, 3).collect(Collectors.toSet());
        Map<Integer, Integer> c3 = Stream.of(1, 2, 3).collect(Collectors.toMap(e -> e, e -> e));

        LinkedList<Integer> c4 = Stream.of(1, 2, 3).collect(Collectors.toCollection(LinkedList::new));

        Stream.of(1,2,3).forEach(System.out::println);
        Optional<Integer> any = Stream.of(1, 2, 3).findAny();
        long count = Stream.of(1, 2, 3).count();
        boolean b = Stream.of(1, 2, 3).allMatch(i -> i < 5);
        boolean b2 = Stream.of(1, 2, 3).anyMatch(i -> i < 5);
        boolean b3 = Stream.of(1, 2, 3).noneMatch(i -> i < 5);

        Optional<Integer> reduce = Stream.of(1, 2, 3).reduce((i1, i2) -> i1 + i2);
        Integer reduce2 = Stream.of(1, 2, 3).reduce(0, (i1, i2) -> i1 + i2);

        Optional<Integer> max = Stream.of(1, 2, 3).max(Comparator.naturalOrder());
        Optional<String> min = Stream.of("aaa", "bb", "dddd").min(Comparator.comparingInt(String::length));
    }
}
