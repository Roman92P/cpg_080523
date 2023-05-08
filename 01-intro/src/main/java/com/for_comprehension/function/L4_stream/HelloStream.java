package com.for_comprehension.function.L4_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HelloStream {

    public static void main(String[] args) {
    }

    public static void l1_stream_not_reusable() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        Stream<Integer> stream = ints.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    public static void l2_stream_lazy() {
        Stream.of(1, 2, 3)
            .map(i -> {
                System.out.println(i);
                return i * 2;
            });

        // intermediate: map, flatmap, filter, peek
        // terminal: collect, foreach, findAny, findFirst,

    }

    public static void l3_count_optimized() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);

        long count = ints.stream()
            .map(i -> i)
            .map(i -> i)
            .map(i -> i)
            .map(i -> i)
            .map(i -> i)
            .map(i -> i)
            .map(i -> i)
            .map(i -> {
                System.out.println("do something");
                return i;
            })
            .filter(i -> true)
            .count();

        System.out.println(count);

    }

}
