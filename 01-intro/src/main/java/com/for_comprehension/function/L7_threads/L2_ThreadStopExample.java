package com.for_comprehension.function.L7_threads;

public class L2_ThreadStopExample {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHolder holder = new ConcurrentHolder();

        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    break;
                }

                System.out.println("t1: " + holder.increment());
            }
        });
        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    break;
                }

                System.out.println("t2: " + holder.increment());

            }
        });
        t1.start();
        t2.start();

        Thread.sleep(10000);

        t1.interrupt();
    }

    public static class ConcurrentHolder {

        private volatile int state = 0;

        private volatile int counter = 0;

        public synchronized int increment() {
            if (state != 0) {
                throw new IllegalStateException("tak byc nie moze!");
            }
            state = 42;
            uninterruptedSleep(500);
            counter++;

            state = 0;
            return counter;
        }

    }

    private static void uninterruptedSleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
