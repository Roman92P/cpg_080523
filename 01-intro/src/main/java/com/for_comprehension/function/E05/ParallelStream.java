package com.for_comprehension.function.E05;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

class ParallelStream {

    static <T, R> List<R> parallelSync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return null;
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task,
        ExecutorService executor) {
        return null;
    }

    private static <R> CompletableFuture<List<R>> reduceToList(List<CompletableFuture<R>> futures) {
        return null;
    }

}
