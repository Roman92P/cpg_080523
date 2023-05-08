package com.for_comprehension.function.E01;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

final class FunctionalInterfaces {
    private FunctionalInterfaces() {
    }

    /**
     * @return a constant supplier returning 42
     */
    static Supplier<Integer> L1_toConstant() {
        return () -> {
            return 42;
        };
    }

    /**
     * @return a function that takes an input String and returns its uppercased version
     */
    static Function<String, String> L2_toUpperCase() {
        return String::toUpperCase;
    }

    /**
     * @return a function that converts strings to longs
     */
    static Function<String, Long> L3_toLong() {
        return Long::parseLong;
    }

    /**
     * @return a predicate that returns true if integer is bigger than 42
     */
    static IntPredicate L4_to42IntegerPredicate() {
        return i -> {
            return i > 42;
        };
    }

    /**
     * @return a higher-order function that takes an integer and returns a predicate validating if the input is bigger than the provided value
     */
    static Function<Integer, Predicate<Integer>> L5_toIntegerPredicate() {
        return i -> {
            return k -> k > i;
        };
    }

    /**
     * @return a function that converts a string into URI instance
     */
    static Function<String, URI> L6_toURI() {
        return s -> {
            try {
                return new URI(s);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * @return a function that takes a Supplier instance and converts it into a Callable instance
     */
    static <T> Function<Supplier<T>, Callable<T>> L7_toCallable() {
        return s -> {
            return s::get;
        };
    }

    /**
     * @return combine two functions into a single one so that
     * the second one is applied directly to the result of the application of the first one
     */
    static <T> BinaryOperator<Function<T, T>> L8_functionComposition() {
        return (f1, f2) -> {
            return (a) -> f2.apply(f1.apply(a));
        };
    }

    public static <T> T todo() {
        throw new RuntimeException("TODO");
    }
}
