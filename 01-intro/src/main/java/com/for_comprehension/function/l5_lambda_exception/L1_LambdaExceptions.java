package com.for_comprehension.function.l5_lambda_exception;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class L1_LambdaExceptions {

    public static void main(String[] args){

        try {
            Stream.of(1, 2, 3)
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)))
                .map(unchecked(i -> process(i)));
        }
        catch (Exception e) {
            System.out.println("something went wrong");
        }

    }

    public interface ThrowingFunction<T,R> {
        R apply(T t) throws Exception;
    }

    public static <T> T process(T input) throws Exception {
        return input;
    }

    public static <T, R> Function<T,R> unchecked(ThrowingFunction<T,R> action) {
        Objects.requireNonNull(action);
        return t -> {
            try {
                return action.apply(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
