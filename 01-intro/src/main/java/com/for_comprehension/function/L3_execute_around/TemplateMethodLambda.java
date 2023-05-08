package com.for_comprehension.function.L3_execute_around;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.function.Supplier;

public class TemplateMethodLambda {

    public static void main(String[] args) {
        runWithLogging(() -> sayHello());

        String message = timed(() -> getMessage());
        // duration: 500ms

        System.out.println(message);
    }

    public static void sayHello() {
        System.out.println("Hello!");
    }

    public static String getMessage() {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
        }
        return "Hello World";
    }

    public static void runWithLogging(Runnable runnable) {
        System.out.println("entering a method " + LocalTime.now());
        runnable.run();
        System.out.println("exiting a method " + LocalTime.now());
    }

    public static <T> T timed(Supplier<T> op) {
        Instant before = Instant.now();
        T result = op.get();
        Instant after = Instant.now();
        System.out.println("duration: " + Duration.between(before, after).toMillis() + "ms");
        return result;
    }

    // loguje czas trwania metody w ms i jednocześnie zwraca wynik operacji
}
