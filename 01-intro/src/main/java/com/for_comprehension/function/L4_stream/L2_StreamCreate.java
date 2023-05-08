package com.for_comprehension.function.L4_stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class L2_StreamCreate {

    public static void main(String[] args) {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.generate(() -> 1);
        Stream<Integer> s3 = Stream.iterate(0, i -> i + 1);
        Stream<Integer> s4 = Stream.concat(Stream.of(1, 2, 3), Stream.of(4));
        LongStream s5 = LongStream.range(0, 100);
        IntStream s6 = IntStream.rangeClosed(0, 100);
        Stream<Integer> s7 = Arrays.asList(1, 2, 3).stream();
    }
}
