package com.for_comprehension.function.L4_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class L8_CustomCollectors {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3).stream()
            .collect(toImmutableList());

        System.out.println(list);
        list.add(4);
    }

    public static <T> Collector<T, ?, List<T>> toImmutableList() {
        return new ImmutableListCollector<>();
    }

    public static class ImmutableListCollector<T> implements Collector<T, ArrayList<T>, List<T>> {

        @Override
        public Supplier<ArrayList<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<ArrayList<T>, T> accumulator() {
            return ArrayList::add;
        }

        @Override
        public BinaryOperator<ArrayList<T>> combiner() {
            return (ts, ts2) -> {
                ts.addAll(ts2);
                return ts;
            };
        }

        @Override
        public Function<ArrayList<T>, List<T>> finisher() {
            return Collections::unmodifiableList;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }

    }

}
