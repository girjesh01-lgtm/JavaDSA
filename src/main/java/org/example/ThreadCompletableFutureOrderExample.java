package org.example;

import java.util.concurrent.CompletableFuture;

public class ThreadCompletableFutureOrderExample {

    /*
     * Topic: Thread Coordination Using CompletableFuture
     *
     * Problem:
     * Print all odd numbers from 1 to 20 first, then print all even numbers from
     * 1 to 20.
     *
     * Requirement:
     * 1. The odd-number task should print: 1 3 5 7 9 11 13 15 17 19
     * 2. The even-number task should print: 2 4 6 8 10 12 14 16 18 20
     * 3. The even-number task should run only after the odd-number task has
     *    completely finished.
     *
     * Key idea:
     * CompletableFuture lets you chain async tasks.
     *
     * In this example:
     * - runAsync() starts the odd task asynchronously
     * - thenRun() schedules the even task after the odd task completes
     * - join() makes main wait until the whole chain is complete
     */
    public static void main(String[] args) {
        CompletableFuture<Void> printTask = CompletableFuture
                .runAsync(() -> {
                    for (int i = 1; i <= 20; i += 2) {
                        System.out.println(Thread.currentThread().getName() + " -> " + i);
                    }
                })
                .thenRun(() -> {
                    for (int i = 2; i <= 20; i += 2) {
                        System.out.println(Thread.currentThread().getName() + " -> " + i);
                    }
                });

        System.out.println(Thread.currentThread().getName() + " waits for chain");
        printTask.join();
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
