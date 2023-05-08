package com.for_comprehension.function.l3_execute_around;

import java.time.LocalTime;

public class TemplateMethodGof {

    public static void main(String[] args) {
        new HelloWithLogging().run();
    }

    public static void sayHello() {
        System.out.println("Hello!");
    }

    public static class HelloWithLogging extends LoggingTemplateMethod {

        @Override
        void runInternal() {
            System.out.println("Hello!");
        }
    }

    public static abstract class LoggingTemplateMethod {
        abstract void runInternal();

        public void run() {
            System.out.println("entering a method " + LocalTime.now());
            runInternal();
            System.out.println("exiting a method " + LocalTime.now());
        }
    }






}
