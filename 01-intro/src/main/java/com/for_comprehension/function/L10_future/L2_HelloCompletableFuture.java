package com.for_comprehension.function.L10_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class L2_HelloCompletableFuture {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(4);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "", e);
        // ...
        cf1
            .thenApplyAsync(i -> process(i), e)
            .thenApplyAsync(i -> process(i), e)
            .thenApplyAsync(i -> process(i), e)
            .thenApplyAsync(i -> process(i), e)
            .thenAccept(System.out::println);
        System.out.println("hello");
    }

    public static CompletableFuture<String> getFooBy(String name) {
        return CompletableFuture.supplyAsync(() -> name + "John");
    }

    public static void l1_basics() {
        ExecutorService e = Executors.newFixedThreadPool(2);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> processToString(42), e);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> processToString(17), e);
        cf1
            .applyToEither(cf2, s -> s)
            .thenApply(str -> str + "foo")  // map(str -> str + "foo")
            .thenApply(String::toUpperCase)
            .thenAccept(System.out::println);
        System.out.println("doing something else...");
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

    public static String processToString(Integer input) {
        System.out.println("processing... " + input + " on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(10000) + 1000);
        }
        catch (InterruptedException e) {
        }
        return String.valueOf(input);
    }

}
