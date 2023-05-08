package com.for_comprehension.function.E02.original;

import java.util.Optional;

// facade/strangler-pattern
public class OptionalsNew {

    private final OptionalsLegacy delegate;

    OptionalsNew(OptionalsLegacy delegate) {
        this.delegate = delegate;
    }

    Optional<OptionalsLegacy.Person> findPerson(int id) {
        return Optional.ofNullable(delegate.findPerson(id));
    }

    Optional<String> findAddress(OptionalsLegacy.Person person) {
        return Optional.ofNullable(delegate.findAddress(person));
    }

    Optional<String> findAddressById(int id) {
        return Optional.ofNullable(delegate.findAddressById(id));
    }

}
