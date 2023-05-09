package com.for_comprehension.function.L7_threads;

public class L1_ThreadStopExample {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHolder holder = new ConcurrentHolder();

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                }

                try {
                    System.out.println("t1: " + holder.increment());
                }
                catch (InterruptedException e) {
                }

            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                }

                try {
                    System.out.println("t2: " + holder.increment());
                }
                catch (InterruptedException e) {
                }

            }
        });
        t1.start();
        t2.start();

        Thread.sleep(10000);

        t1.stop();
    }

    public static class ConcurrentHolder {

        private volatile int state = 0;
        private volatile int counter = 0;

        public synchronized int increment() throws InterruptedException {
            if (state != 0) {
                throw new IllegalStateException("tak byc nie moze!");
            }
            state = 42;


            Thread.sleep(500);
            counter++;

            state = 0;
            return counter;
        }


    }


}
