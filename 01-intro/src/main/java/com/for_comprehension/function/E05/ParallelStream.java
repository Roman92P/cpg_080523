package com.for_comprehension.function.E05;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class ParallelStream {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(4);
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> integers = parallelSync(list, el -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
            }
            catch (InterruptedException ex) {
            }
            return el;
        }, e);

        e.shutdown();
    }

    static <T, R> List<R> parallelSync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return parallelAsync(input, task, executor).join();
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task,
        ExecutorService executor) {
        return input.stream()
            .map(e -> CompletableFuture.supplyAsync(() -> task.apply(e), executor))
            .collect(collectingAndThen(toList(), ParallelStream::reduceToList));
    }

    private static <R> CompletableFuture<List<R>> reduceToList(List<CompletableFuture<R>> futures) {
        CompletableFuture<Void> result = CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0]));

        for (CompletableFuture<R> future : futures) {
            future.whenComplete((t, throwable) -> {
                if (throwable != null) {
                    result.completeExceptionally(throwable);
                }
            });
        }

        return result
            .thenApply(__ -> futures)
            .thenApply(f -> f.stream()
                .map(CompletableFuture::join)
                .collect(toList()));
    }

}
