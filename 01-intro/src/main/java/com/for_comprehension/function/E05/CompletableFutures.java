package com.for_comprehension.function.E05;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.for_comprehension.function.misc.User;
import com.for_comprehension.function.misc.UsersClient;

class CompletableFutures {

    private static final UsersClient usersClient = new UsersClient();

    /**
     * Complete incoming {@link CompletableFuture} manually with value 42
     */
    static void L1_manualCompletion(CompletableFuture<Integer> future) {
        future.complete(42);
    }

    /**
     * Complete incoming {@link CompletableFuture} exceptionally with a {@link NullPointerException}
     */
    static void L2_manualExceptionCompletion(CompletableFuture<Integer> future) {
        future.completeExceptionally(new NullPointerException());
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} asynchronously Use the
     * provided id to look up the user
     */
    static CompletableFuture<User> L3_runAsync(Integer id) {
        return CompletableFuture.supplyAsync(() -> usersClient.getUserById(id));
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} asynchronously on a given
     * thread pool Use the provided id to look up the user
     * <p>
     * Essentially, the same as above + execution on a provided thread pool
     */
    static CompletableFuture<User> L4_runAsyncOnACustomPool(Integer id, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> usersClient.getUserById(id), executor);
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} on two different ids and
     * return both users in a List
     * <p>
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     */
    static CompletableFuture<List<User>> L5_runAsyncAndCombine(int id, int id2) {
        CompletableFuture<User> u1 = CompletableFuture.supplyAsync(() -> usersClient.getUserById(id));
        CompletableFuture<User> u2 = CompletableFuture.supplyAsync(() -> usersClient.getUserById(id2));

        return u1.thenCombine(u2, Arrays::asList);
    }

    /**
     * Return a combined future which completes with a value of the first completed future
     * <p>
     * {@link CompletableFuture#applyToEither(CompletionStage, Function)}
     */
    static CompletableFuture<Integer> L6_composeFutures(
        CompletableFuture<Integer> f1,
        CompletableFuture<Integer> f2) {

        return f1.applyToEither(f2, i -> i);
    }

    /**
     * Given two futures, return the result of whichever completes first
     * <p>
     * {@link CompletableFuture#anyOf(CompletableFuture[])}
     */
    static <T> T L7_returnValueOfTheFirstCompleted(CompletableFuture<T> f1, CompletableFuture<T> f2) {
        return CompletableFuture.anyOf(f1, f2).thenApply(i -> (T) i).join();
    }

    /**
     * Given a list of futures, convert it to a future containing a list of all results
     * <p>
     * {@link CompletableFuture#allOf(CompletableFuture[])}
     */
    static <T> CompletableFuture<List<T>> L8_returnResultsAsList(List<CompletableFuture<T>> futures) {
        // https://shipilev.net/blog/2016/arrays-wisdom-ancients/
        CompletableFuture<Void> result = CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0]));

        for (CompletableFuture<T> future : futures) {
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
                .collect(Collectors.toList()));
    }

    public static <T> T todo() {
        throw new RuntimeException("TODO");
    }

}
