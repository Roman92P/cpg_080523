package com.for_comprehension.function.l3_execute_around;

import java.time.LocalTime;

public class TemplateMethodLambda {

    public static void main(String[] args) {
        runWithLogging(() -> sayHello());
    }

    public static void sayHello() {
        System.out.println("Hello!");
    }

    public static void runWithLogging(Runnable runnable) {
        System.out.println("entering a method " + LocalTime.now());
        runnable.run();
        System.out.println("exiting a method " + LocalTime.now());
    }
}
