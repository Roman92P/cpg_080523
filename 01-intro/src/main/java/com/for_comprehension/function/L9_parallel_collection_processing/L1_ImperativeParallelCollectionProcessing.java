package com.for_comprehension.function.L9_parallel_collection_processing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L1_ImperativeParallelCollectionProcessing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> ints = IntStream.range(0, 10).boxed().collect(Collectors.toList());
       /* List<String> list = ints.stream()
            .map(i -> processToString(i))
            .collect(Collectors.toList());*/

        ExecutorService e = new ThreadPoolExecutor(4, 6,
            1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(20), namedThreadFactory("collection-processing"));

        List<String> results = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();
        for (Integer i : ints) {
            futures.add(e.submit(() -> processToString(i)));
        }

        for (Future<String> future : futures) {
            results.add(future.get());
        }

        System.out.println(results);

        e.shutdown();
    }

    public static String processToString(Integer input) {
        System.out.println("processing... " + input + " on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        }
        catch (InterruptedException e) {
        }
        return String.valueOf(input);
    }

    private static ThreadFactory namedThreadFactory(String prefix) {
        return r -> new Thread(r, prefix + "-" + UUID.randomUUID());
    }

}
