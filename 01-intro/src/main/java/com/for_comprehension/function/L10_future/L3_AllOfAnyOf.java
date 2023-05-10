package com.for_comprehension.function.L10_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class L3_AllOfAnyOf {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(4);

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> process(1), e);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> process(2), e);
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        }, e);

        Object join = CompletableFuture.anyOf(cf1, cf2, cf3).join();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(cf1, cf2, cf3);

        cf2.whenComplete((integer, throwable) -> {
            if (throwable != null) {
                // costam
            }
            else {

            }
        });
    }
    public static <T> T process(T input) {
        System.out.println("processing... " + input + " on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000) + 1000);
        }
        catch (InterruptedException e) {
        }
        return input;
    }
}
