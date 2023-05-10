package com.for_comprehension.function.L10_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class L1_HelloFuture {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(2);
        Future<Integer> future = e.submit(() -> 42);

        try {
            Integer integer = future.get();
            System.out.println(integer);
        }
        catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        catch (ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }

}
