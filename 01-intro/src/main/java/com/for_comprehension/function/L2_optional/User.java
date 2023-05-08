package com.for_comprehension.function.L2_optional;

import java.util.Objects;
import java.util.Optional;

public class User {

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
