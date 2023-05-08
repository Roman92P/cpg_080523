package com.for_comprehension.function.L4_stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class L3_StreamProcess {

    public static void main(String[] args) {
        Stream.of(1, 2, 3)
            .map(i -> i + 1)
            .forEach(System.out::println);

        System.out.println("---");

        Stream.of(1, 2, 3)
            .filter(i -> i % 2 == 1)
            .map(i -> i + 1)
            .forEach(System.out::println);

        System.out.println("---");

        Stream.of(3, 2, 1)
            .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);

        System.out.println("---");

        Stream.of(1, 2, 3, 4)
            .skip(2)
            .distinct()
            .limit(1)
            .forEach(System.out::println);

        System.out.println("---");

        Stream.of(1, 2, 3, 4)
            .dropWhile(i -> i < 3)
            .takeWhile(i -> i > 2)
            .forEach(System.out::println);

    }
}
