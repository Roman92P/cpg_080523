package com.for_comprehension.function.L8_thread_pools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class L3_MemoryLeakDemo {

    private static final List<LocalDate> dates = new ArrayList<>();

    public static void main(String[] args) {
        Stream.iterate(LocalDate.now(), year -> year.plusDays(1))
            .map(i -> {
                dates.add(i);
                return i;
            }).forEach(a -> {});
    }

}
