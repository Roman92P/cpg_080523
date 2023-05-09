package com.for_comprehension.function.L7_threads;

public class L4_MaxThreads {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(20000);
        while (true) {
            Thread.sleep(2);
            new Thread(() -> {
                try {
                    Thread.sleep(10000000);
                }
                catch (InterruptedException e) {
                }
            }).start();
        }
    }
}
