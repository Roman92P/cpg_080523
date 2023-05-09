package com.for_comprehension.function.l5_lambda_exception;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class L2_SneakyThrows {

    public static void main(String[] args) {
        Stream.of(1, 2, 3)
            .map(sneaky(i -> process(i)))
            .forEach(s -> {});
    }

    public interface ThrowingFunction<T,R> {
        R apply(T t) throws Exception;
    }

    public static <T> T process(T input) throws Exception {
        throw new Exception("checked exception!");
    }

    public static <T, R> Function<T,R> sneaky(ThrowingFunction<T,R> action) {
        Objects.requireNonNull(action);
        return t -> {
            try {
                return action.apply(t);
            }
            catch (Exception e) {
                return rethrowSneaky(e);
            }
        };
    }

    public static <T extends Exception, R> R rethrowSneaky(Exception ex) throws T {
        throw (T) ex;
    }

}
