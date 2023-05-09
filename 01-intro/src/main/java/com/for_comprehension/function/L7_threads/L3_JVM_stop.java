package com.for_comprehension.function.L7_threads;

public class L3_JVM_stop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                }
                System.out.println("Hello!");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
