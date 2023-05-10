package com.for_comprehension.function.E04;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToStringJoiningCollector {

    public static void main(String[] args) {
        String c1 = Stream.of(1, 2, 3)
            .collect(joiningAsStrings());

        String c2 = Stream.of(1, 2, 3)
            .collect(joiningAsStrings(","));

        String c3 = Stream.of(1, 2, 3)
            .collect(joiningAsStrings(",", "{", "}"));

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }

    public static <T> Collector<T, ?, String> joiningAsStrings() {
        return null; // TODO
    }

    public static <T> Collector<T, ?, String> joiningAsStrings(String separator) {
        return null; // TODO
    }

    public static <T> Collector<T, ?, String> joiningAsStrings(String separator, String prefix, String postfix) {
        return null; // TODO
    }

}
