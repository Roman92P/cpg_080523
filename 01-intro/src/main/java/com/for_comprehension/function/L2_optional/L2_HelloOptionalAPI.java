package com.for_comprehension.function.L2_optional;

import java.util.Optional;

public class L2_HelloOptionalAPI {

    public static void main(String[] args) {
        Optional<Integer> o1 = Optional.ofNullable(42);
        Optional<Integer> o2 = Optional.of(42);

        findUserById(4)
            .map(i -> 2)
            .filter(i -> i % 2 == 0)
            .map(i -> i + 2)
            .flatMap(i -> findUserById(2))
//            .orElse("foo);
//            .orElseGet(() -> calculateDefault());
//            .orElseThrow(IllegalStateException::new)
//            .ifPresent(System.out::println);
            .or(() -> findUserById(3));
    }

    static String calculateDefault() {
        try {
            Thread.sleep(100000);
        }
        catch (InterruptedException e) {
        }

        return "42";
    }

    static Optional<String> findUserById(int id) {
        if (id == 5) {
            return Optional.of("John");
        }

        return Optional.empty();
    }
}
