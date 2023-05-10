package com.for_comprehension.function.L8_thread_pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class L2_CachedThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService e = new ThreadPoolExecutor(0, 400,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
        for (int i = 0; i < 1100000000; i++) {
            int temp = i;
            e.submit(() -> process(temp));
        }

        //        e.shutdown();
    }

    public static <T> T process(T input) {
        System.out.println("processing... " + input + " on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
        }
        return input;
    }

}
