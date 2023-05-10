package com.for_comprehension.function.L10_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class L4_Cancellation {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService e = Executors.newFixedThreadPool(1);

        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500000);
            }
            catch (InterruptedException ex) {
                System.out.println("Interrupted! closing!");
            }
            return 42;
        }, e);
        Thread.sleep(1000);
        f.cancel(true);

        e.shutdown();



    }

}
