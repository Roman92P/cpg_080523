package com.for_comprehension.function.E02;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static java.util.function.Predicate.not;

class OptionalsRefactor {

    private Optional<Person> findPerson(int id) {
        switch (id) {
            case 1:
                return Optional.of(new Person("James", 48, 193, LocalDate.of(2000, Month.NOVEMBER, 1)));
            case 2:
                return Optional.of(new Person("John", 62, 169, LocalDate.of(1989, Month.OCTOBER, 21)));
            default:
                return Optional.empty();
        }
    }

    private Optional<String> findAddress(Person person) {
        if (person.getBirthDate().isAfter(LocalDate.of(2000, Month.JANUARY, 1))) {
            return Optional.empty();
        }
        if (person.getBirthDate().isAfter(LocalDate.of(1980, Month.JANUARY, 1))) {
            return Optional.of(" Some St.   ");
        }
        return Optional.empty();
    }

    private Optional<String> findAddressById(int id) {
        return findPerson(id)                   // 1
            .filter(p -> p.getHeight() > 168)   // 2
            .flatMap(this::findAddress)         // 3
            .map(String::trim)                  // 4
            .filter(not(String::isEmpty));      // 5
    }

    // ***
    // DON"T CHANGE ANYTHING BEYOND THIS POINT
    // ***

    private class Person {

        private final String name;

        private final int weight;

        private final int height;

        private final LocalDate birthDate;

        private Person(String name, int weight, int height, LocalDate birthDate) {
            this.name = name;
            this.weight = weight;
            this.height = height;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getHeight() {
            return height;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

    }

}
