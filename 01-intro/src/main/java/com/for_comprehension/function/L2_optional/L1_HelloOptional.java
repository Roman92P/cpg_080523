package com.for_comprehension.function.L2_optional;

import java.util.Objects;
import java.util.Optional;

public class L1_HelloOptional {

    public static void main(String[] args) {
        // imperative
        Optional<String> user = findUserById(42);
        String result2;
        if (user.isPresent()) {
            String s = user.get();
            if (s != null) {
                String upperCase = s.toUpperCase();
                if (upperCase != null) {
                    String postfixxed = upperCase + "postfix";
                    result2 = postfixxed != null
                        ? postfixxed
                        : "default";
                }
            }
        }
        // declarative
        String result = findUserById(42)
            .map(String::toUpperCase)
            .map(u -> u + "postfix")
            .orElse("default");
    }

    static Optional<String> findUserById(int id) {
        if (id == 5) {
            return Optional.of("John");
        }

        return Optional.empty();
    }

    public static class User {

        private final String name;
        private final String address;

        public User(String name, String address) {
            Objects.requireNonNull(name);
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getAddress() {
            return Optional.ofNullable(address);
        }

    }

}
