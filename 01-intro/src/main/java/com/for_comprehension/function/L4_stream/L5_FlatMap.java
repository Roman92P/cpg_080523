package com.for_comprehension.function.L4_stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L5_FlatMap {

    public static void main(String[] args) {
        Optional<Optional<Integer>> integer = Optional.of(Optional.of(2));
        Optional<Integer> integer1 = integer.flatMap(i -> i);

        List<List<Integer>> lists = Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4));
        List<Integer> list2 = lists.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}
