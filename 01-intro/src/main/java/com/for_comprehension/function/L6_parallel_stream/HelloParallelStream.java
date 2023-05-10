package com.for_comprehension.function.L6_parallel_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HelloParallelStream {

    public static void main(String[] args) {
        List<Integer> ints = IntStream.range(0, 20).boxed().collect(Collectors.toList());

        List<Integer> collect = ints.parallelStream()
            .map(HelloParallelStream::process)
            .collect(Collectors.toList());
    }

    public static <T> T process(T input) {
        System.out.println("processing... " + input + " on " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        return input;
    }

}
