package com.for_comprehension.function.L1_function;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class HelloFunction {

    // (int a, int b) -> { return a + b; }
    // (a, b) -> { return a + b; }
    // (a, b) -> a + b;
    // a -> a + 1;
    // () -> 42;
    // () -> {};

    public static void main(String[] args) {
        Function<String, Integer> f1 = str -> Integer.parseInt(str);
        Function<String, Integer> f2 = Integer::parseInt;

        Predicate<Integer> f3 = i -> i % 2 == 0;
        Consumer<Integer> f4 = i -> System.out.println(i);
        Supplier<Integer> f5 = () -> 42;

        UnaryOperator<Integer> f6 = i -> i + 1;
        BinaryOperator<Integer> f7 = (i1, i2) -> i1 + i2;

        Runnable f8 = () -> {
            System.out.println("hello");
        };
        Callable<Integer> f9 = () -> 42;

        BiFunction<String, Integer, String> f10 = (s, integer) -> s + integer;
        TriFunction<String, String, String, String> f11 = (s, s2, s3) -> s + s2 + s3;
    }

    @FunctionalInterface
    public interface TriFunction<T1, T2, T3, R> {
        R apply(T1 t1, T2 t2, T3 t3);
    }

}
