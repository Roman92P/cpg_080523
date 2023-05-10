package com.for_comprehension.function.L11_loom;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LoomDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService e = Executors.newCachedThreadPool();
        //        ExecutorService e = Executors.newVirtualThreadPerTaskExecutor(); JDK 20

        Instant before = Instant.now();
        List<Integer> join = IntStream.range(0, 8000)
            .boxed()
            .map(el -> CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("processing i: " + el);
                    Thread.sleep(10000);
                }
                catch (InterruptedException ex) {
                }
                return el;
            }, e)).collect(collectingAndThen(toList(), LoomDemo::reduceToList))
            .join();

        Instant after = Instant.now();

        System.out.println("Duration: " + Duration.between(before, after).toMillis() + "ms");

        e.shutdown();
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
