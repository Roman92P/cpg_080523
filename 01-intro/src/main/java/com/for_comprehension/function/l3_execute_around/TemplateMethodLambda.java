package com.for_comprehension.function.l3_execute_around;

import java.time.LocalTime;

public class TemplateMethodLambda {

    public static void main(String[] args) {
        runWithLogging(() -> sayHello());

        String message = timed(() -> getMessage());
        // duration: 500ms
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

    // TODO
    public static ??? timed(??? ???) {
        ???
    }

    // loguje czas trwania metody w ms i jednocześnie zwraca wynik operacji
}
