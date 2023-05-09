package com.for_comprehension.function.L4_stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class L7_StreamCollect {

    public static void main(String[] args) {
        String c1 = Stream.of("a", "bb", "ccc", "dd")
            .collect(Collectors.joining(":", "-->", "<--"));

        Map<Integer, List<String>> c2 = Stream.of("a", "bb", "ccc", "dd", "bb")
            .collect(groupingBy(str -> str.length()));

        Map<Integer, Set<String>> c3 = Stream.of("a", "bb", "ccc", "dd", "bb")
            .collect(
                groupingBy(str -> str.length(),
                    filtering(s -> s.length() < 2,
                        toSet())));

        System.out.println(c3);

        String c4 = Stream.of("a", "bb", "ccc", "dd", "bb")
            .collect(Collectors.collectingAndThen(Collectors.joining(), s -> s + ":collectingAndThen"));

        System.out.println(c4);

        //        Stream.of("a", "bb", "ccc", "dd", "bb").collect(Collectors.teeing(toList(), toSet(), (strings,
        //        strings2) -> ))

    }

}
