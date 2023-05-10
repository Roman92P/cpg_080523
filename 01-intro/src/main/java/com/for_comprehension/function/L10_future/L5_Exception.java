package com.for_comprehension.function.L10_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L5_Exception {
    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(1);

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 42, e);

        cf1
            .thenApply(i -> i)
            .thenApply(i -> {
                throw new RuntimeException("whoopsie");
            })
            .exceptionally(throwable -> "saved!")
            .thenAccept(System.out::println)
            .join();
    }
}
