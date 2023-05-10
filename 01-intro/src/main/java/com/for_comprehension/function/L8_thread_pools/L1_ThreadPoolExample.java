package com.for_comprehension.function.L8_thread_pools;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class L1_ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {
        // corePoolSize - ile wątków będzie utrzymywane w puli cały czas
        // maximumPoolSize - ile max wątków może pula tymczasowo stworzy
        // keepAliveTime - jak długo dodatkowe wątki mogą trwać w bezczynności
        LinkedBlockingQueue<Runnable> workingQueue = new LinkedBlockingQueue<>(100);
        ExecutorService e = new ThreadPoolExecutor(4, 50,
            1, TimeUnit.MINUTES,
            workingQueue, numberedThreadFactory("mail_scheduler"), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 110; i++) {
            int temp = i;
            e.submit(() -> process(temp));
        }

        //        e.shutdown();
    }

    private static ThreadFactory namedThreadFactory(String prefix) {
        return r -> new Thread(r, prefix + "-" + UUID.randomUUID());
    }

    private static ThreadFactory numberedThreadFactory(String prefix) {
        return new ThreadFactory() {

            private final AtomicInteger counter = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, prefix + "-" + counter.getAndIncrement());
            }
        };
    }

    public static <T> T process(T input) {
        System.out.println("processing... " + input + " on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        return input;
    }

}
